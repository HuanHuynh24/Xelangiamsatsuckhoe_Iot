package com.example.gimstsckho_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gimstsckho_iot.fragment.HomeFragment;
import com.example.gimstsckho_iot.fragment.UserFragment;
import com.example.gimstsckho_iot.fragment.MapsFragment;
import com.example.gimstsckho_iot.fragment.SecondFragment;
import com.example.gimstsckho_iot.model.ConSQL;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    Connection connection;
    Fragment selectedFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation bottom
        ActionBar actionBar = getSupportActionBar();
        bottomNavigationView = findViewById(R.id.navigation_bottom);


        // setOnclickNavigation Bottom
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Home);
        Intent intent = getIntent();
        if(intent != null){
            String fragment = intent.getStringExtra("fragment");
            if(Objects.equals(fragment, "UserFragment")){
                bottomNavigationView.setSelectedItemId(R.id.person);
            }
        }


    }



    private void showNotification(String title, String message) {
        NotificationHelper.showNotification(this, title, message);
    }

    HomeFragment firstFragment = new HomeFragment();
    SecondFragment secondFragment = new SecondFragment();
    UserFragment forthFragment = new UserFragment();

    MapsFragment mapsFragment = new MapsFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Home){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, firstFragment)
                    .commit();
            return true;
        }
        if(id == R.id.xuly)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, secondFragment)
                    .commit();
            return  true;
        }
        if(id == R.id.map)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, mapsFragment)
                    .commit();
            return true;
        }
        if(id == R.id.person)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, forthFragment)
                    .commit();
            return true;
        }
        return false;
    }
}