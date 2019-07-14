package com.example.zomatoapp;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    public static final String GO_OUT_FRAGMENT_TAG = "go_out_fragment";
    public static final String ORDER_FRAGMENT_TAG = "order_fragment";
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(ORDER_FRAGMENT_TAG);
                    if (fragment==null) {
                        OrderFragment orderFragment = new OrderFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_frame, orderFragment, ORDER_FRAGMENT_TAG)
                                .commit();
                    }
                    return true;
                case R.id.navigation_go_out:
                    Fragment goOutFrag = getSupportFragmentManager().findFragmentByTag(GO_OUT_FRAGMENT_TAG);
                    if (goOutFrag==null){
                    GoOutFragment goOutFragment = new GoOutFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_frame,goOutFragment, GO_OUT_FRAGMENT_TAG)
                            .commit();}
                    return true;
                case R.id.navigation_gold:
                    GoldFragment goldFragment=new GoldFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,goldFragment).commit();
                    return true;
                case R.id.navigation_search:
                    Toast.makeText(getApplicationContext(),"search selected",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_frame,profileFragment)
                            .commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        OrderFragment orderFragment = new OrderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_frame,orderFragment)
                .commit();
        Bundle bundle=getIntent().getBundleExtra("locationBundle");
        if(bundle!=null){
            Log.d("Message","onStart is called"+bundle.getString("place"));
            orderFragment.setArguments(bundle);
        }
      }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1){
//        if(resultCode==12){
//            Log.d("message", "the problem is not here");
//            Bundle bundle=data.getBundleExtra("locationBundle");
//            OrderFragment orderFragment=new OrderFragment();
//            orderFragment.setArguments(bundle);
//        }
//    }}
}
