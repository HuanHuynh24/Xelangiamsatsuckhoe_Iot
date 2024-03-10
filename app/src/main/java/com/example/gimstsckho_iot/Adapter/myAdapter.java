package com.example.gimstsckho_iot.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.model.ItemAdapterHome;

import java.util.ArrayList;

public class myAdapter extends ArrayAdapter<ItemAdapterHome> {
   Activity context;
   ArrayList<ItemAdapterHome> mylist;
   int layout;

    public myAdapter(Activity context1, ArrayList<ItemAdapterHome> mylist, int layout) {
        super(context1 , layout, mylist);
        this.context = context1;
        this.mylist = mylist;
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        convertView = inflater.inflate(layout, null);
        ItemAdapterHome myFunction = mylist.get(position);

        TextView index = convertView.findViewById(R.id.IndexItem);
        index.setText(myFunction.getIndex()+"");

        TextView nameItem = convertView.findViewById(R.id.nameItem);
        nameItem.setText(myFunction.getImgName());

        LottieAnimationView view = convertView.findViewById(R.id.imageItem);
        view.setAnimation(myFunction.getImg());
        return  convertView;
    };
}
