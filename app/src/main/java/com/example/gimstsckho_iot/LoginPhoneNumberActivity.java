package com.example.gimstsckho_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText logInPhonenumber;
    Button btnSendOtp;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);

        progressBar = findViewById(R.id.login_progress_bar);
        logInPhonenumber = findViewById(R.id.login_mobile_number);
        btnSendOtp = findViewById(R.id.send_otp_btn);
        countryCodePicker = findViewById(R.id.login_countrycode);

        countryCodePicker.registerCarrierNumberEditText(logInPhonenumber);
        btnSendOtp.setOnClickListener(v -> {
            if(!countryCodePicker.isValidFullNumber()){
                logInPhonenumber.setError("Số điện thoại không chính xác");
                return;
            }
            Intent intent = new Intent(LoginPhoneNumberActivity.this, LoginOtpActivity.class);
            intent.putExtra("phone", countryCodePicker.getFormattedFullNumber());
            startActivity(intent);
        });
    }

}