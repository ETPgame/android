package com.example.sz_bigtest2;

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
import java.util.List;

public class LandscapeAdapter extends ArrayAdapter<Landscape> {
    private Context context;
    private ArrayList<Landscape> list;
    public LandscapeAdapter(@NonNull Context context, ArrayList<Landscape> list){
        super(context,android.R.layout.simple_list_item_1,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        if(convertView == null){
            v= LayoutInflater.from(context).inflate(R.layout.row_view,null,false);
        }else {
            v=convertView;
        }
        ImageView iv=v.findViewById(R.id.row_view_iv);
        TextView tv=v.findViewById(R.id.row_view_tv);
        Landscape landscape = list.get(position);
        iv.setImageResource(landscape.getPicId());
        tv.setText(landscape.getName());
        return v;
    }
}
