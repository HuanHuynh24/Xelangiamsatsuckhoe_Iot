package com.example.gimstsckho_iot.model;

import static androidx.core.content.ContextCompat.startActivities;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

public class showEnableGPSDialog {
    Context context;

    public showEnableGPSDialog(Context context) {
        this.context = context;
    }

    public void handleEnableGPSDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Vui lòng bật định vị GPS để sử dụng các tính năng của ứng dụng.")
                .setCancelable(false)
                .setPositiveButton("Cài Đặt", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }



}
