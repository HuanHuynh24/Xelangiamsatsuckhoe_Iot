package com.example.gimstsckho_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LoginOtpActivity extends AppCompatActivity {


    String phoneNumber;
    EditText loginOtp;
    Button btnLoginNext;
    ProgressBar progressBar;
    TextView textResend;
    String mVerificationId ;
    PhoneAuthProvider.ForceResendingToken mResendToken;

    long timeSecond = 60L;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);

        phoneNumber = getIntent().getExtras().getString("phone");

        btnLoginNext = findViewById(R.id.login_next_btn);
        loginOtp = findViewById(R.id.login_otp);
        progressBar = findViewById(R.id.login_progress_bar);
        textResend = findViewById(R.id.resent_otp_text);

        Toast.makeText(LoginOtpActivity.this, phoneNumber, Toast.LENGTH_SHORT).show();
        sendOtp(phoneNumber, false);

        btnLoginNext.setOnClickListener(v -> {
            String enterOtp = loginOtp.getText().toString();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, enterOtp);
            if(enterOtp.length()==6){
                signIn(credential);
            } else {
                loginOtp.setError("Mã không chính xác");
            }
        });

        textResend.setOnClickListener(v -> {
            sendOtp(phoneNumber, true);
        });

    }

    private void sendOtp(String phoneNumber, boolean isResend){
        isPresent(isResend);
        setTextResend();
        PhoneAuthOptions.Builder options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(timeSecond, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signIn(phoneAuthCredential);
                                isPresent(false);
                            }



                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(LoginOtpActivity.this, "OTP verification failed", Toast.LENGTH_SHORT).show();
                                isPresent(false);

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                isPresent(false);
                                mVerificationId = s;
                                mResendToken = forceResendingToken;
                                Toast.makeText(LoginOtpActivity.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(options.setForceResendingToken(mResendToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(options.build());
        }

    }

    private void setTextResend(){
        textResend.setEnabled(false);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeSecond--;
                textResend.setText("Resent OTP in "+timeSecond+" seconds");
                if(timeSecond<=0){
                    timeSecond = 60L;
                    timer.cancel();
                    runOnUiThread(() -> {
                        textResend.setEnabled(true);
                    });
                }
            }
        },0, 1000);
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        isPresent(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginOtpActivity.this, LoginUserActivity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginOtpActivity.this, "Mã không chính xác", Toast.LENGTH_SHORT).show();
                    isPresent(false);
                }
            }
        });
    }
    private void isPresent(boolean isResent){
        if(isResent){
            btnLoginNext.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            btnLoginNext.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}