package com.example.gimstsckho_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gimstsckho_iot.model.SaveSharedPreferences;
import com.example.gimstsckho_iot.model.handlePhonenumber;
import com.example.gimstsckho_iot.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class HandleLoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPhonenumber;
    EditText edtOtp;
    Button btnSendOtp;
    Button btnLogin;
    ProgressBar progressBar;

    long timeSecond = 60L;
    TextView tvResendOtp;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mforceResendingToken;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String phonenumber = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_login);

        edtUsername = findViewById(R.id.edtNameuserLogin);
        edtPhonenumber = findViewById(R.id.edtPhonenumber);
        edtOtp = findViewById(R.id.edtOtp);
        btnSendOtp = findViewById(R.id.btn_sendOtp_login);
        progressBar = findViewById(R.id.LoginProgressBar);
        btnLogin = findViewById(R.id.btn_login);
        tvResendOtp = findViewById(R.id.tvResendOtp);

        edtOtp.setEnabled(false);
        btnLogin.setVisibility(View.GONE);
        progressBar.setVisibility(View.INVISIBLE);

        btnSendOtp.setOnClickListener(v -> {
            username= edtUsername.getText().toString();
            phonenumber = edtPhonenumber.getText().toString();
            phonenumber=handlePhonenumber.handlePhonenumber(phonenumber);
            progressBar.setVisibility(View.VISIBLE);
            handleAccount(username, phonenumber);
        });


        btnLogin.setOnClickListener(v -> {
            String enterOtp = edtOtp.getText().toString();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, enterOtp);
            signIn(credential);

        });

        tvResendOtp.setOnClickListener(v -> {
            handleSendOtp(phonenumber, true);
        });

    }

    private void handleAccount(String username, String phonenumber){
        FirebaseUtil.userQueryUser()
                .whereEqualTo("userName", username)
                .whereEqualTo("phoneNumber", phonenumber)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        if(snapshots.isEmpty()){
                            isExistUsername(username);
                            isExistPhonenumber(phonenumber);
                        } else {
                            handleSendOtp(phonenumber, false);
                            btnLogin.setVisibility(View.VISIBLE);
                            btnLogin.setEnabled(false);
                            btnSendOtp.setVisibility(View.GONE);
                            edtOtp.setEnabled(true);
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void isExistUsername(String username){
        FirebaseUtil.userQueryUser()
                .whereEqualTo("userName", username)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        if (snapshots.isEmpty()){
                            edtUsername.setError("Tên đăng nhập không chính xác");
                        }
                    }
                });
    };

    private void isExistPhonenumber(String phonenumber){
        FirebaseUtil.userQueryUser()
                .whereEqualTo("phoneNumber", phonenumber)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        if (snapshots.isEmpty()){
                            edtPhonenumber.setError("Số điện thoại không chính xác");
                        }
                    }
                });
    };

    private void handleSendOtp(String phonenumber, boolean isResend){
        isPresent(isResend);
        setTextResend();
        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phonenumber)
                .setActivity(this)
                .setTimeout(timeSecond, TimeUnit.SECONDS)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(HandleLoginActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        isPresent(false);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(HandleLoginActivity.this, "OTP verification failed", Toast.LENGTH_SHORT).show();
                        isPresent(false);

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        mVerificationId = s;
                        mforceResendingToken = forceResendingToken;
                        Toast.makeText(HandleLoginActivity.this, "Mời nhập mã OTP", Toast.LENGTH_SHORT).show();
                        isPresent(false);
                        btnLogin.setEnabled(true);
                    }
                });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(mforceResendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential){
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent =new Intent(HandleLoginActivity.this, MainActivity.class);
                    SaveSharedPreferences.SaveSharedPreferences(HandleLoginActivity.this, phonenumber, username);

                    startActivity(intent);
                } else {
                    Toast.makeText(HandleLoginActivity.this, "Mã OTP không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTextResend(){
        tvResendOtp.setEnabled(false);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeSecond--;
                tvResendOtp.setText("Resent OTP in "+timeSecond+" seconds");
                if(timeSecond<=0){
                    timeSecond = 60L;
                    timer.cancel();
                    runOnUiThread(() -> {
                        tvResendOtp.setEnabled(true);
                    });
                }
            }
        },0, 1000);
    }
    private void isPresent(boolean isResent){
        if(isResent){
            btnLogin.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}