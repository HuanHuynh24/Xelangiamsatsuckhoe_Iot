package com.example.gimstsckho_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    Connection connection;
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

        // Xử lý dữ liệu trên cơ sở dũ liệu
//        show();

      
   
    }


    public void show(){
        ConSQL c= new ConSQL();
        connection = c.conclass();
        if(c!=null){
            try{
                String sqlstatement = "Select * from BacSi";
                Statement smt = connection.createStatement();
                ResultSet set = smt.executeQuery(sqlstatement);
                while (set.next()){
                    Toast.makeText(this, set.getString(2), Toast.LENGTH_SHORT).show();
                }
                connection.close();
            }catch (Exception e){
                Log.e("Error is", e.getMessage());
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