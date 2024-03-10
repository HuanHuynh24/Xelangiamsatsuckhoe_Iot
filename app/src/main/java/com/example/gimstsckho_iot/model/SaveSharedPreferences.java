package com.example.gimstsckho_iot.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;

import com.example.gimstsckho_iot.HandleLoginActivity;
import com.example.gimstsckho_iot.MainActivity;

public class SaveSharedPreferences {


    public static void SaveSharedPreferences(Context context, String phonenumber, String username){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phonenumber);
        editor.putString("userName", username);
        editor.apply();
    }
}
