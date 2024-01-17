package com.example.sz_test3_6;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {
    private Context context;
    private List<City> list;

    public CityAdapter(@NonNull Context context, List<City> list){
        super(context, android.R.layout.simple_list_item_1,list);

        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            v= LayoutInflater.from(context).inflate(R.layout.row_view,null,false);
        }
        ImageView iv=v.findViewById(R.id.row_view_iv);
        TextView tv_name=v.findViewById(R.id.row_tv_name);
        TextView tv_phone=v.findViewById(R.id.row_tv_phone);
        ImageView iv_call=v.findViewById(R.id.row_view_iv_call);
        City city=list.get(position);
        iv.setImageResource(city.getPicId());
        String phone=city.getPhone();
        if (TextUtils.isEmpty(phone)) {
            iv_call.setVisibility(View.GONE);
        }else {
            iv_call.setVisibility(View.VISIBLE);
        }
        tv_name.setText(city.getName());
        tv_phone.setText(city.getPhone());
        String.valueOf()

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Calling"+phone,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
