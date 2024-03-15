package com.example.gimstsckho_iot.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.model.LocationPermissions;
import com.example.gimstsckho_iot.model.LocationUpdater;
import com.example.gimstsckho_iot.model.showEnableGPSDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    private float distance;
    private String username;
    private LocationUpdater locationUpdater;
    private Marker markerUser;
    private Marker markerWheelchair;
    private  LocationPermissions locationPermissions;
    private  SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_maps, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         sharedPreferences = requireContext().getSharedPreferences("Account", Context.MODE_PRIVATE);


        locationPermissions = new LocationPermissions(getActivity());
        locationPermissions.HandleLocationPermissions();

         username = sharedPreferences.getString("userName","");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        locationUpdater = new LocationUpdater(requireContext());
        locationUpdater.setLocationUpdateListener(new LocationUpdater.LocationUpdateListener() {
            @Override
            public void onLocationUpdate(LatLng latLng) {
                if(markerUser == null){
                    markerUser = mMap.addMarker(new MarkerOptions().position(latLng).title(username));
                } else {
                    markerUser.setPosition(latLng);
                }
                if(markerWheelchair != null){
                    LatLng position1 = markerUser.getPosition();
                    LatLng position2 = markerWheelchair.getPosition();
                    distance =  calculateDistance(position1, position2);
                }
            }
        });
        locationUpdater.startUpdatingLocation();
        LatLng position2 = new LatLng(10.762622, 106.660172);
        markerWheelchair = mMap.addMarker(new MarkerOptions().position(position2).title(username+" - Thiết bị")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position2, 13f));
    }

    private float calculateDistance(LatLng position1, LatLng position2) {
        float[] results = new float[1];
        Location.distanceBetween(position1.latitude, position1.longitude, position2.latitude, position2.longitude, results);
        return results[0];
    }
}