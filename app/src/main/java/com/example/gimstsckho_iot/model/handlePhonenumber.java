package com.example.gimstsckho_iot.model;

public class handlePhonenumber {
    public static String handlePhonenumber(String StringPhoneNumber){

        String s="+84";
        int i =1;
        while (i<StringPhoneNumber.length()){
            s=s+" ";
            s = s+StringPhoneNumber.substring(i, i+3);
            i=i+3;
        }
        return s;
    }
}
