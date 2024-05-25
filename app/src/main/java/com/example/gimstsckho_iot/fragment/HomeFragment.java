////package com.example.gimstsckho_iot.fragment;
////
////import android.content.Context;
////import android.content.SharedPreferences;
////import android.os.Bundle;
////
////import androidx.annotation.NonNull;
////import androidx.fragment.app.Fragment;
////
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.GridView;
////import android.widget.Toast;
////
////import com.example.gimstsckho_iot.model.ItemAdapterHome;
////import com.example.gimstsckho_iot.R;
////import com.example.gimstsckho_iot.Adapter.myAdapter;
////import com.example.gimstsckho_iot.model.SaveSharedPreferences;
////import com.example.gimstsckho_iot.model.showEnableGPSDialog;
////import com.example.gimstsckho_iot.model.userInformation;
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////import java.util.ArrayList;
////
////
////public class HomeFragment extends Fragment {
////
////    int imgRaws [] = {R.raw.animation_blood_pressure, R.raw.animation_heart, R.raw.animation_oxy, R.raw.animation_temperature, R.raw.animation_weight};
////    String imgName[] = {"Huyết áp", "Nhịp tim", "Nồng độ oxy", "Nhiệt độ", "Cân nặng"};
////
////    GridView gv;
////    ArrayList<ItemAdapterHome> mylist;
////    myAdapter myArrayAdapter;
////
////
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////    }
////
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
////        showEnableGPSDialog showEnableGPSDialog = new showEnableGPSDialog(getActivity());
////        if(!showEnableGPSDialog.isGPSEnabled()){
////            showEnableGPSDialog.handleEnableGPSDialog();
////        }
////
////        SharedPreferences sharedPreferences = sharedPreferences = requireContext().getSharedPreferences("Account", Context.MODE_PRIVATE);
////        String username = sharedPreferences.getString("userName", null);
////
////
////
////
////        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
////        DatabaseReference userRef = database.child("users").child(username);
//////        Log.e("username", String.valueOf(userRef));
////        userRef.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                gv = rootView.findViewById(R.id.girdview);
////                mylist = new ArrayList<>();
////                for(int i=0;i< imgName.length; i++){
////                    String indexItem = "";
////                    if(imgName[i].equals("Huyết áp"))
////                    {
////                        indexItem = snapshot.child("bPressure").child("dsp").getValue(Integer.class)+"/"+snapshot.child("bPressure").child("sbp").getValue(Integer.class)+" mmHG";
////                    }
////                    else
////                    if(imgName[i].equals("Nhịp tim"))
////                        indexItem = snapshot.child("heartbeat").getValue(Double.class)+" Bpm";
////                    else
////                    if(imgName[i].equals("Nồng độ oxy"))
////                        indexItem = snapshot.child("SpO2").getValue(Double.class)+"%";
////                    else
////                    if(imgName[i].equals("Nhiệt độ"))
////                        indexItem = snapshot.child("temperature").getValue(Double.class)+" ^C";
////                    else indexItem = snapshot.child("weight").getValue(Double.class)+ " KG";
////                    mylist.add(new ItemAdapterHome(imgRaws[i], indexItem, imgName[i] ));
////
////                }
////                myArrayAdapter = new myAdapter(getActivity(), mylist, R.layout.backgroud_item_function);
////                gv.setAdapter(myArrayAdapter);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////
////
////        return  rootView;
////    }
////
////
////}
//package com.example.gimstsckho_iot.fragment;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.GridView;
//import android.widget.Toast;
//
//import com.example.gimstsckho_iot.model.ItemAdapterHome;
//import com.example.gimstsckho_iot.R;
//import com.example.gimstsckho_iot.Adapter.myAdapter;
//import com.example.gimstsckho_iot.model.showEnableGPSDialog;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment {
//
//    int imgRaws[] = {R.raw.animation_blood_pressure, R.raw.animation_heart, R.raw.animation_oxy, R.raw.animation_temperature, R.raw.animation_weight};
//    String imgName[] = {"Huyết áp", "Nhịp tim", "Nồng độ oxy", "Nhiệt độ", "Cân nặng"};
//
//    GridView gv;
//    ArrayList<ItemAdapterHome> mylist;
//    myAdapter myArrayAdapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View rootView, Bundle savedInstanceState) {
//        super.onViewCreated(rootView, savedInstanceState);
//        showEnableGPSDialog showEnableGPSDialog = new showEnableGPSDialog(requireActivity());
//        if (!showEnableGPSDialog.isGPSEnabled()) {
//            showEnableGPSDialog.handleEnableGPSDialog();
//        }
//
//        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Account", Context.MODE_PRIVATE);
//        String username = sharedPreferences.getString("userName", null);
//
//        if (username == null) {
//            Toast.makeText(requireContext(), "Username not found in SharedPreferences", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference userRef = database.child("users").child(username);
//
//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                gv = rootView.findViewById(R.id.girdview);
//                mylist = new ArrayList<>();
//                for (int i = 0; i < imgName.length; i++) {
//                    String indexItem = "";
//                    switch (imgName[i]) {
//                        case "Huyết áp":
//                            indexItem = snapshot.child("bPressure").child("dsp").getValue(Integer.class) + "/" +
//                                    snapshot.child("bPressure").child("sbp").getValue(Integer.class) + " mmHG";
//                            break;
//                        case "Nhịp tim":
//                            indexItem = snapshot.child("heartbeat").getValue(Double.class) + " Bpm";
//                            break;
//                        case "Nồng độ oxy":
//                            indexItem = snapshot.child("SpO2").getValue(Double.class) + "%";
//                            break;
//                        case "Nhiệt độ":
//                            indexItem = snapshot.child("temperature").getValue(Double.class) + " ^C";
//                            break;
//                        default:
//                            indexItem = snapshot.child("weight").getValue(Double.class) + " KG";
//                            break;
//                    }
//                    mylist.add(new ItemAdapterHome(imgRaws[i], indexItem, imgName[i]));
//                }
//                myArrayAdapter = new myAdapter(requireActivity(), mylist, R.layout.backgroud_item_function);
//                gv.setAdapter(myArrayAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
package com.example.gimstsckho_iot.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import com.example.gimstsckho_iot.model.ItemAdapterHome;
import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.Adapter.myAdapter;
import com.example.gimstsckho_iot.model.showEnableGPSDialog;
import com.example.gimstsckho_iot.viewmodel.UserDataViewModel;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    int imgRaws[] = {R.raw.animation_blood_pressure, R.raw.animation_heart, R.raw.animation_oxy, R.raw.animation_temperature, R.raw.animation_weight};
    String imgName[] = {"Huyết áp", "Nhịp tim", "Nồng độ oxy", "Nhiệt độ", "Cân nặng"};

    GridView gv;
    ArrayList<ItemAdapterHome> mylist;
    myAdapter myArrayAdapter;
    private UserDataViewModel userDataViewModel;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDataViewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        if (context != null) {
            showEnableGPSDialog showEnableGPSDialog = new showEnableGPSDialog(requireActivity());
            if (!showEnableGPSDialog.isGPSEnabled()) {
                showEnableGPSDialog.handleEnableGPSDialog();
            }

            SharedPreferences sharedPreferences = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("userName", null);

            if (username == null) {
                Toast.makeText(context, "Username not found in SharedPreferences", Toast.LENGTH_SHORT).show();
                return;
            }

            gv = rootView.findViewById(R.id.girdview);
            mylist = new ArrayList<>();
            myArrayAdapter = new myAdapter(requireActivity(), mylist, R.layout.backgroud_item_function);
            gv.setAdapter(myArrayAdapter);

            userDataViewModel.getUserData(username).observe(getViewLifecycleOwner(), new Observer<List<ItemAdapterHome>>() {
                @Override
                public void onChanged(List<ItemAdapterHome> itemAdapterHomes) {
                    if (itemAdapterHomes != null) {
                        mylist.clear();
                        mylist.addAll(itemAdapterHomes);
                        myArrayAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}

