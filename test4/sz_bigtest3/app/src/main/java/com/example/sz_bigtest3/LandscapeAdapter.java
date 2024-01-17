package com.example.sz_bigtest3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LandscapeAdapter extends ArrayAdapter<Landscape> {
    private Context context;
    private ArrayList<Landscape> list;
    private int type = 1;

    public LandscapeAdapter(Context context, ArrayList<Landscape> list){
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;
    }
    public LandscapeAdapter(Context context, ArrayList<Landscape> list, int type){
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        if(type == 1) {
            v = LayoutInflater.from(context).inflate(R.layout.list_row_view, null, false);
        } else{
            v = LayoutInflater.from(context).inflate(R.layout.grid_row_view, null, false);
        }


        TextView tv = v.findViewById(R.id.row_view_tv);
        ImageView img = v.findViewById(R.id.row_view_iv);
        tv.setText(list.get(position).getName());
        img.setImageResource(list.get(position).getImg());
        return v;
    }
}
