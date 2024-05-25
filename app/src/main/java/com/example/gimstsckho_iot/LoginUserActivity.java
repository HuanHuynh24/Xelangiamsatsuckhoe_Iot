package com.example.gimstsckho_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gimstsckho_iot.model.BPressure;
import com.example.gimstsckho_iot.model.SaveSharedPreferences;
import com.example.gimstsckho_iot.model.userInformation;
import com.example.gimstsckho_iot.model.userModel;
import com.example.gimstsckho_iot.util.FirebaseHelper;
import com.example.gimstsckho_iot.util.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class LoginUserActivity extends AppCompatActivity {

    String phoneNumber;
    EditText loginUsername;
    Button btnLoginLetInMe;
    ProgressBar progressBar;
    userModel userModel;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        phoneNumber = getIntent().getExtras().getString("phone");

        loginUsername = findViewById(R.id.login_username);
        btnLoginLetInMe = findViewById(R.id.login_let_in_me_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        isPresent(false);
        btnLoginLetInMe.setOnClickListener(v -> {
            isPresent(true);
            setUsername();
        });
    }
    private  void setUsername(){

        userName = loginUsername.getText().toString();
        if(userName.length()<=3 | userName.isEmpty())
        {
            loginUsername.setError("Username có độ dài ít nhất 3 kí tự");
            isPresent(false);
            return;
        }
        getUsername(userName);

    }
    private void getUsername(String userName){
            FirebaseUtil.userQueryUser()
                    .whereEqualTo("userName", userName)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            if(snapshots.isEmpty()){
                                handleNotFoundUsername(userName);
                            } else
                                handleFoundUsername();
                        }
                    });
    }

    private void handleFoundUsername(){
        isPresent(false);
        loginUsername.setError("Tên người dùng đã tồn tại !!!");
    }
    private void handleNotFoundUsername(String userName){
        userModel = new userModel(phoneNumber, userName, Timestamp.now());
        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
                    SaveSharedPreferences.SaveSharedPreferences(LoginUserActivity.this, phoneNumber, userName, null);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    FirebaseHelper firebaseHelper = new FirebaseHelper();
                    firebaseHelper.addUser(userName);

                    startActivity(intent);
                }
            }
        });
    }

    private void isPresent(boolean isResent){
        if(isResent){
            btnLoginLetInMe.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            btnLoginLetInMe.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}