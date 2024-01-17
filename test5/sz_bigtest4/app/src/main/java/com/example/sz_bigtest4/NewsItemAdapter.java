package com.example.sz_bigtest4;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsItemAdapter extends ArrayAdapter<NewsItem> {
    private Context context;
    private List<NewsItem> list;


    public NewsItemAdapter(@NonNull Context context, List<NewsItem> list) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null) {
            v = LayoutInflater.from(context).inflate(R.layout.row_view,parent,false);
        }
        NewsItem newItem = list.get(position);
        TextView tv_title = v.findViewById(R.id.row_view_tv_title);
        TextView tv_time = v.findViewById(R.id.row_view_tv_time);
        ImageView iv = v.findViewById(R.id.row_view_iv);
        tv_title.setText(newItem.getTitle());
        tv_time.setText(newItem.getTime());
        String imgSrc = newItem.getImgSrc();
        Glide.with(context).load(Uri.parse(imgSrc)).into(iv);
        return v;
    }
}
