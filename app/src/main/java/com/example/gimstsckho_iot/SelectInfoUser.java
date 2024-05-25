package com.example.gimstsckho_iot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gimstsckho_iot.fragment.UserFragment;
import com.example.gimstsckho_iot.model.SaveSharedPreferences;
import com.example.gimstsckho_iot.model.handlePhonenumber;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectInfoUser extends AppCompatActivity {
    public ImageView arrow;
    public EditText username, numberphone, dateofbirth, address;
    public RadioButton male, fermale;
    public SharedPreferences sharedPreferences;
    public RadioGroup gioitinh;
    public Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_info_user);
        arrow = findViewById(R.id.arrow);
        username = findViewById(R.id.username);
        numberphone = findViewById(R.id.numberphone);
        dateofbirth = findViewById(R.id.dateofbirth);
        dateofbirth.setOnClickListener(view -> {
            // Lấy ngày hiện tại
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Tạo DatePickerDialog và hiển thị
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateofbirth.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });
        address = findViewById(R.id.address);
        male = findViewById(R.id.male);
        fermale = findViewById(R.id.fermale);
        gioitinh = findViewById(R.id.gioitinh);
        btnSave = findViewById(R.id.btnsave);
        sharedPreferences = this.getSharedPreferences("Account", Context.MODE_PRIVATE);
        informationSettings();
        arrow.setOnClickListener((e)->{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("fragment", "UserFragment");
            startActivity(intent);
        });
        btnSave.setOnClickListener(e -> handleSaveInfor());
    }

    public void  handleSaveInfor(){
        String name = username.getText().toString();
        String sdt = numberphone.getText().toString();
        String ngsinh = dateofbirth.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(ngsinh);

        } catch (ParseException e) {

            dateofbirth.setError("Ngày không hợp lệ! Định dạng phải là dd/MM/yyyy");
            return;
        }
        String dchi = address.getText().toString();
        String gtinh;
        if(male.isChecked()){
             gtinh = "Nam";
        } else {
             gtinh = "Nữ";
        }

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userDocRef = FirebaseFirestore.getInstance().collection("users").document(userId);
        userDocRef.update("Ngaysinh", ngsinh)
                .addOnSuccessListener(aVoid -> {
                    SaveSharedPreferences.setngaysinh(this, ngsinh);
                })
                .addOnFailureListener(e -> {
                });
        userDocRef.update("userFullName", name)
                .addOnSuccessListener(aVoid -> {
                    SaveSharedPreferences.setuserFullName(this, name);
                })
                .addOnFailureListener(e -> {
                });
        if(sdt.length()==10){
            userDocRef.update("phoneNumber", handlePhonenumber.handlePhonenumber(sdt))
                    .addOnSuccessListener(aVoid -> {
                        SaveSharedPreferences.setphonenumber(this, sdt);
                    })
                    .addOnFailureListener(e -> {
                    });
        }
        userDocRef.update("diachi", dchi)
                .addOnSuccessListener(aVoid -> {
                    SaveSharedPreferences.setdiachi(this, dchi);
                })
                .addOnFailureListener(e -> {
                });
        userDocRef.update("gioitinh", gtinh)
                .addOnSuccessListener(aVoid -> {
                    SaveSharedPreferences.setgioitinh(this, gtinh);
                })
                .addOnFailureListener(e -> {
                });
        Toast.makeText(this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
    }
    private void informationSettings() {
        String name = sharedPreferences.getString("userFullName", null);
        String phone = sharedPreferences.getString("phone", null);
        String ngaysinh = sharedPreferences.getString("ngaysinh", new Date().toString());
        String diachi = sharedPreferences.getString("diachi", null);
        String gioitinh = sharedPreferences.getString("gioitinh", "Nam");

        username.setText(name);
        numberphone.setText(phone);
        dateofbirth.setText(ngaysinh);
        address.setText(diachi);
        if(gioitinh.equals("Nam")){
            male.setChecked(true);
        } else fermale.setChecked(true);
    }
}