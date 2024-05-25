package com.example.gimstsckho_iot.model;

import com.google.android.gms.maps.model.LatLng;

public class userInformation {
    public Double SpO2;
    public BPressure bPressure;
    public CustomLatLng locationGPS;
    public   Double weight;
    public Double heartbeat;
    public  Double temperature;
    public userInformation() {
    }

    public userInformation(Double spO2, BPressure bPressure, CustomLatLng locationGPS, Double weight, Double heartbeat, Double temperature) {
        SpO2 = spO2;
        this.bPressure = bPressure;
        this.locationGPS = locationGPS;
        this.weight = weight;
        this.heartbeat = heartbeat;
        this.temperature = temperature;
    }

    public userInformation(Double spO2,  Double weight) {
        SpO2 = spO2;
        this.weight = weight;
    }


}
