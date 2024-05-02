package com.example.gimstsckho_iot.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;

import com.example.gimstsckho_iot.HandleLoginActivity;
import com.example.gimstsckho_iot.MainActivity;

import java.util.Date;

public class SaveSharedPreferences {

    public static void SaveSharedPreferences(Context context, String phonenumber, String username, String imageUser){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phonenumber);
        editor.putString("userName", username);
        editor.putString("avatarUser", imageUser);
        editor.apply();
    }


    public static void setphonenumber(Context context, String phonenumber){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phonenumber);
        editor.apply();
    }
    public static void setusername(Context context, String username){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", username);
        editor.apply();
    }
    public static void setuserFullName(Context context, String username){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userFullName", username);
        editor.apply();
    }
    public static void setimageUser(Context context, String imageUser){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("avatarUser", imageUser);
        editor.apply();
    }
    public static void setngaysinh(Context context,String ngaysinh){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ngaysinh", ngaysinh);
        editor.apply();
    }
    public static void setdiachi(Context context,String diachi){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("diachi", diachi);
        editor.apply();
    }
    public static void setgioitinh(Context context,String gioitinh){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("gioitinh", gioitinh);
        editor.apply();
    }

}
