package com.example.gimstsckho_iot.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.gimstsckho_iot.model.ItemAdapterHome;
import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.Adapter.myAdapter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    int imgRaws [] = {R.raw.animation_blood_pressure, R.raw.animation_heart, R.raw.animation_oxy, R.raw.animation_temperature, R.raw.animation_weight};
    String imgName[] = {"Huyết áp", "Nhịp tim", "Nồng độ oxy", "Nhiệt độ", "Cân nặng"};
    Double index[] = {65.2, 343.4, 34.0, 37.5, 62.0};

    GridView gv;
    ArrayList<ItemAdapterHome> mylist;
    myAdapter myArrayAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        gv = rootView.findViewById(R.id.girdview);
        mylist = new ArrayList<>();
        for(int i=0;i< imgName.length; i++){
            String indexItem = "";
            if(imgName[i].equals("Huyết áp"))
            {
                indexItem = index[i]+" mmHG";
            }
            else
                if(imgName[i].equals("Nhịp tim"))
                    indexItem = index[i]+" Bpm";
                else
                    if(imgName[i].equals("Nồng độ oxy"))
                        indexItem = index[i]+" %";
                    else
                    if(imgName[i].equals("Nhiệt độ"))
                        indexItem = index[i]+" ^C";
                    else indexItem = index[i]+ " KG";
            mylist.add(new ItemAdapterHome(imgRaws[i], indexItem, imgName[i] ));

        }
        myArrayAdapter = new myAdapter(getActivity(), mylist, R.layout.backgroud_item_function);
        gv.setAdapter(myArrayAdapter);
        return  rootView;
    }


}