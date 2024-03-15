package com.example.gimstsckho_iot.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gimstsckho_iot.OptionLoginActivity;
import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.model.ItemAdapterOption;
import com.example.gimstsckho_iot.Adapter.myAdapter_Listview;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class UserFragment extends Fragment {

    private final int[] idImageOptions = {R.drawable.ic_person};
    private final String[] nameImageOptions = {"Thông tin cá nhân"};

    private final int[] idImageAccount = {R.drawable.ic_person};
    private final String[] nameImageAccount = {"Đăng xuất"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_user, container, false);

        TextView info_username = rootview.findViewById(R.id.info_username);
        TextView phonenumber_user = rootview.findViewById(R.id.phonenumber_user);
        ListView lv_overview = rootview.findViewById(R.id.lv_overview);

        // Đặt số điện thoại và tên người dùng
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Account", Context.MODE_PRIVATE);
        String phonenumber = sharedPreferences.getString("phone", null);

        String username = sharedPreferences.getString("userName", null);
        info_username.setText("Tên người dùng: " + username);
        phonenumber_user.setText("Số điện thoại: " + phonenumber);

        // Thêm các cài đặt cho người dùng
        ArrayList<ItemAdapterOption> myArray = new ArrayList<>();
        for (int i = 0; i < idImageOptions.length; i++) {
            myArray.add(new ItemAdapterOption(idImageOptions[i], nameImageOptions[i]));
        }
        myAdapter_Listview myAdapterListview = new myAdapter_Listview(getActivity(), myArray, R.layout.backgroud_item_option);
        lv_overview.setAdapter(myAdapterListview);

        ListView lv_account = rootview.findViewById(R.id.lv_account);
        myArray = new ArrayList<>();
        for (int i = 0; i < idImageAccount.length; i++) {
            myArray.add(new ItemAdapterOption(idImageAccount[i], nameImageAccount[i]));
        }
        myAdapterListview = new myAdapter_Listview(getActivity(), myArray, R.layout.backgroud_item_option);
        lv_account.setAdapter(myAdapterListview);

        lv_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), OptionLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        return rootview;
    }
}