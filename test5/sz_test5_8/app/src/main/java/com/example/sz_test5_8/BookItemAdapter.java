package com.example.sz_test5_8;

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

public class BookItemAdapter extends ArrayAdapter<BookItem> {
    private Context context;
    private List<BookItem> list;

    public BookItemAdapter(@NonNull Context context, List<BookItem> list) {
        super(context, android.R.layout.simple_list_item_1,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            v= LayoutInflater.from(context)
                    .inflate(R.layout.row_view,parent,false);

        }
        BookItem bookItem=list.get(position);
        TextView tv_title=v.findViewById(R.id.row_view_tv_title);
        TextView tv_author=v.findViewById(R.id.row_view_tv_author);
        ImageView iv=v.findViewById(R.id.row_view_iv);
        tv_title.setText(bookItem.getTitle());
        tv_author.setText(bookItem.getAuthor());
        String imgSrc=bookItem.getImgSrc();
        Glide.with(context).load(Uri.parse(imgSrc)).into(iv);

        return v;
    }
}

