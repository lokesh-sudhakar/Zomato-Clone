package com.example.zomatoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.zomatoapp.viewModels.MapsViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener{

    private double latitude;
    private double longitude;
    private String address;
    private MapsViewModel mapsViewModel = new MapsViewModel();
    private GoogleMap mMap;
    private PlacesClient mPlacesClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private boolean mLocationPermissionGranted;
    TextView locationTextView;
    AutocompleteSupportFragment placeAutoComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        locationTextView=findViewById(R.id.location);
        TextView addAddress=findViewById(R.id.addAddress);
        final EditText enterAddress=findViewById(R.id.enterAddress);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }
        placeAutoComplete = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG,Place.Field.ID, Place.Field.NAME));
        placeAutoComplete.setCountry("IN");
        placeAutoComplete.getView().setBackgroundColor(Color.WHITE);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.i("message", "Place: " + place.getName() + ", " + place.getId());
                LatLng selectedPlaceLatLng=place.getLatLng();
                Log.d("message"," "+place.getLatLng());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(selectedPlaceLatLng));
                mapsViewModel.getAddress(selectedPlaceLatLng.latitude,selectedPlaceLatLng.longitude,MapsActivity.this);
                latitude=selectedPlaceLatLng.latitude;
                longitude=selectedPlaceLatLng.longitude;
                address=place.getName();
                locationTextView.setText(address);

            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i("message", "An error occurred: " + status);

            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.getBackground().setAlpha(0);
        String apiKey = getString(R.string.google_maps_key);
        Places.initialize(getApplicationContext(), apiKey);
        mPlacesClient = Places.createClient(this);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Button locationButton=findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapsActivity.this,HomeActivity.class);
                Bundle bundle=new Bundle();
                bundle.putDouble("latitude",latitude);
                bundle.putDouble("longitude", longitude);
                bundle.putString("place", address);
                intent.putExtra("locationBundle", bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAddress.setVisibility(View.VISIBLE);
            }
        });
        enterAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                enterAddress.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_geolocate:
                pickCurrentPlace();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = mapsViewModel.onRequestPermission(requestCode, grantResults);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mLocationPermissionGranted = mapsViewModel.getLocationPermission(this);
        mMap.setOnCameraMoveListener(this);
        pickCurrentPlace();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000m
                pickCurrentPlace();
            }
        }, 1000);
    }

    private void pickCurrentPlace() {
        if (mMap == null) {
            return;
        }
        if (mLocationPermissionGranted) {
            Location location = mapsViewModel.getDeviceLocation(mFusedLocationProviderClient, mPlacesClient, mMap, this);
            if (location == null) {
                Log.d("the prob is here", " yesssssssssssssssssssssssssss");
            }
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d("message", ": bbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + latitude);
                address = mapsViewModel.getAddress(latitude, longitude, this);
                locationTextView.setText(address);
            }
        } else {

            mLocationPermissionGranted = mapsViewModel.getLocationPermission(this);
        }
    }

    @Override
    public void onCameraMove() {
        mMap.clear();
        findViewById(R.id.imgLocationPinUp).setVisibility(View.VISIBLE);
        LatLng position=mMap.getCameraPosition().target;
        latitude=position.latitude;
        longitude=position.longitude;
        address=mapsViewModel.getAddress(position.latitude,position.longitude,this);
        locationTextView.setText(address);
    }
}
