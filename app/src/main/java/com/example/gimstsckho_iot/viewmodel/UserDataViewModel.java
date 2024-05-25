package com.example.gimstsckho_iot.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.model.ItemAdapterHome;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class UserDataViewModel extends ViewModel {

    private final MutableLiveData<List<ItemAdapterHome>> userData = new MutableLiveData<>();

    public LiveData<List<ItemAdapterHome>> getUserData(String username) {
        loadUserData(username);
        return userData;
    }

    private void loadUserData(String username) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = database.child("users").child(username);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ItemAdapterHome> items = new ArrayList<>();
                int imgRaws[] = {R.raw.animation_blood_pressure, R.raw.animation_heart, R.raw.animation_oxy, R.raw.animation_temperature, R.raw.animation_weight};
                String imgName[] = {"Huyết áp", "Nhịp tim", "Nồng độ oxy", "Nhiệt độ", "Cân nặng"};

                for (int i = 0; i < imgName.length; i++) {
                    String indexItem = "";
                    switch (imgName[i]) {
                        case "Huyết áp":
                            indexItem = snapshot.child("bPressure").child("dsp").getValue(Integer.class) + "/" +
                                    snapshot.child("bPressure").child("sbp").getValue(Integer.class) + " mmHG";
                            break;
                        case "Nhịp tim":
                            indexItem = snapshot.child("heartbeat").getValue(Double.class) + " Bpm";
                            break;
                        case "Nồng độ oxy":
                            indexItem = snapshot.child("Sp02").getValue(Double.class) + "%";
                            break;
                        case "Nhiệt độ":
                            indexItem = snapshot.child("temperature").getValue(Double.class) + " ^C";
                            break;
                        default:
                            indexItem = snapshot.child("weight").getValue(Double.class) + " KG";
                            break;
                    }
                    items.add(new ItemAdapterHome(imgRaws[i], indexItem, imgName[i]));
                }
                userData.setValue(items);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
