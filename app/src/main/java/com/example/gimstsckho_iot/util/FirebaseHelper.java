package com.example.gimstsckho_iot.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gimstsckho_iot.model.BPressure;
import com.example.gimstsckho_iot.model.CustomLatLng;
import com.example.gimstsckho_iot.model.userInformation;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    private DatabaseReference mDatabase;
    public FirebaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void addUser(String userId) {
        userInformation user = new userInformation(0.0,new BPressure(0,0), new CustomLatLng(10.12345, 106.6789),0.0, 0.0,0.0);
        mDatabase.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
                Log.e("Thong bao", "thanh cong");
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Log.e("loi tai thong tin", e.getMessage());
           }
       });
    }



    public void getUser(String userId, final OnGetDataListener<userInformation> listener) {
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInformation user = dataSnapshot.getValue(userInformation.class);
                listener.onSuccess(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError.toException());
            }
        });
    }
}
