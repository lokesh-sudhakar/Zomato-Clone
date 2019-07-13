package com.example.zomatoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zomatoapp.viewModels.MapsViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
    }

    private void pickCurrentPlace() {
        if (mMap == null) {
            return;
        }
        if (mLocationPermissionGranted) {
            Location location=mapsViewModel.getDeviceLocation(mFusedLocationProviderClient, mPlacesClient, mMap, this);
            if(location!=null){
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            address=mapsViewModel.getAddress(latitude,longitude,this);
        }} else {
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(mDefaultLocation)
                    .snippet(getString(R.string.default_info_snippet)));
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
    }


}
