package com.example.gimstsckho_iot.model;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

public class LocationUpdater {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LocationUpdateListener locationUpdateListener;

    public LocationUpdater(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                if(locationUpdateListener != null){
                    locationUpdateListener.onLocationUpdate(newLatLng);
                }
            }
        };
    }
    public void setLocationUpdateListener(LocationUpdateListener listener){
        this.locationUpdateListener = listener;
    }
    public void startUpdatingLocation(){
        try {
            if(isGPSEnabled()) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            }
        }catch (SecurityException e){
            e.printStackTrace();

        }
    }
    public void stopUpdatingLocation() {
        locationManager.removeUpdates(locationListener);
    }
    private boolean isGPSEnabled() {
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public interface LocationUpdateListener {
        void onLocationUpdate(LatLng latLng);
    }
}
