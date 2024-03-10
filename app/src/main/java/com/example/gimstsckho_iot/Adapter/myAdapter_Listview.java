package com.example.gimstsckho_iot.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gimstsckho_iot.R;
import com.example.gimstsckho_iot.model.ItemAdapterOption;

import java.util.ArrayList;

public class myAdapter_Listview extends ArrayAdapter<ItemAdapterOption> {
    private Activity context;
   private  ArrayList<ItemAdapterOption> myArray;
   private int idLayout;

    public myAdapter_Listview(Activity context, ArrayList<ItemAdapterOption> myArray, int idLayout) {
        super(context, idLayout, myArray);
        this.context = context;
        this.myArray = myArray;
        this.idLayout = idLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);

        ItemAdapterOption itemOptions = myArray.get(position);

        ImageView imageView = convertView.findViewById(R.id.option_imageView);
        imageView.setImageResource(itemOptions.getImage());

        TextView textView = convertView.findViewById(R.id.option_textView);
        textView.setText(itemOptions.getNameItemOption());
        return  convertView;
    }
}
