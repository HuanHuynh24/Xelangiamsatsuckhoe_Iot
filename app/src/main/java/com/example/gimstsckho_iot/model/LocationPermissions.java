package com.example.gimstsckho_iot.model;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;




public class LocationPermissions {
    public Context context;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    public LocationPermissions(Context context) {
        this.context = context;
    }

    public void HandleLocationPermissions(){
        if(checkLocationPermission()){
//            Toast.makeText(context, "Đã được cấp quyền", Toast.LENGTH_SHORT).show();
        } else {
            requestLocationPermission();
        }
    }
    private boolean checkLocationPermission(){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(context)
                    .setTitle("Yêu cầu quyền truy cập vị trí")
                    .setMessage("Ứng dụng cần quền truy cập vị trí để hoạt động")
                    .setPositiveButton("Cho phép", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                        }
                    })
                    .setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

}
