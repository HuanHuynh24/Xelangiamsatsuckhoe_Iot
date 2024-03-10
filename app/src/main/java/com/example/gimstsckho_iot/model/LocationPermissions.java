package com.example.gimstsckho_iot.model;

import android.Manifest;
import android.content.Context;

import androidx.core.content.ContextCompat;

public class LocationPermissions {

    public static void HandleLocationPermissions(){
        if(checkLocationPermission()){

        } else {
            requestLocationPermission();
        }
    }
    private boolean checkLocationPermission(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
