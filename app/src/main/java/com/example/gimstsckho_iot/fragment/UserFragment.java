package com.example.gimstsckho_iot.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gimstsckho_iot.OptionLoginActivity;
import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.model.ItemAdapterOption;
import com.example.gimstsckho_iot.Adapter.myAdapter_Listview;
import com.example.gimstsckho_iot.model.SaveSharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.UUID;

public class UserFragment extends Fragment {

    private final int PICK_IMAGE_REQUEST = 1;

    private ImageView img_avatar;
    private TextView info_username;
    private TextView phonenumber_user;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_user, container, false);

        info_username = rootview.findViewById(R.id.info_username);
        phonenumber_user = rootview.findViewById(R.id.phonenumber_user);
        img_avatar = rootview.findViewById(R.id.image_user);

        sharedPreferences = requireContext().getSharedPreferences("Account", Context.MODE_PRIVATE);

        setUserInfo();

        img_avatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        setupOptionsListView(rootview);

        return rootview;
    }

    private void setUserInfo() {
        String username = sharedPreferences.getString("userName", null);
        String phonenumber = sharedPreferences.getString("phone", null);

        if (username != null)
            info_username.setText("Tên người dùng:"+ username);
        if (phonenumber != null)
            phonenumber_user.setText("Số điện thoại: "+phonenumber);

        String imageUrl = sharedPreferences.getString("avatarUser", "");
        if (!imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(img_avatar);
        }
    }

    private void setupOptionsListView(View rootview) {
        ListView lv_account = rootview.findViewById(R.id.lv_account);

        ArrayList<ItemAdapterOption> myArray = new ArrayList<>();
        myArray.add(new ItemAdapterOption(R.drawable.ic_person, "Đăng xuất"));

        myAdapter_Listview myAdapterListview = new myAdapter_Listview(getActivity(), myArray, R.layout.backgroud_item_option);
        lv_account.setAdapter(myAdapterListview);

        lv_account.setOnItemClickListener((parent, view, position, id) -> logout());


        ListView lv_overview = rootview.findViewById(R.id.lv_overview);

        ArrayList<ItemAdapterOption> myArray1 = new ArrayList<>();
        myArray1.add(new ItemAdapterOption(R.drawable.ic_person, "Thông tin cá nhân"));

        myAdapter_Listview myAdapterListview1 = new myAdapter_Listview(getActivity(), myArray1, R.layout.backgroud_item_option);
        lv_overview.setAdapter(myAdapterListview1);

//        lv_account.setOnItemClickListener();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        sharedPreferences.edit().clear().apply();

        Intent intent = new Intent(getActivity(), OptionLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            uploadImageToFirebaseStorage(selectedImageUri);
        }
    }

    private void uploadImageToFirebaseStorage(Uri imageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images").child(UUID.randomUUID().toString());
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        updateAvatarUrlInFirestore(imageUrl);
                    });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error uploading image", Toast.LENGTH_SHORT).show();
                });
    }

    private void updateAvatarUrlInFirestore(String imageUrl) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userDocRef = FirebaseFirestore.getInstance().collection("users").document(userId);
        userDocRef.update("avatarUser", imageUrl)
                .addOnSuccessListener(aVoid -> {
//                    sharedPreferences.edit().putString("image", imageUrl).apply();
                    SaveSharedPreferences.SaveSharedPreferences(requireContext(), info_username.getText().toString(), phonenumber_user.getText().toString(), imageUrl );
                    Picasso.get().load(imageUrl).into(img_avatar);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error updating avatar", Toast.LENGTH_SHORT).show();
                });
    }
}
