package com.example.sz_test3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv=findViewById(R.id.tv_result);
        sp=findViewById(R.id.spinner);

        Resources res=getResources();
        String[] colorNames=res.getStringArray(R.array.my_color_names);
        TypedArray colorValues=res.obtainTypedArray(R.array.my_color_values);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,colorNames);
        sp.setAdapter(adapter);

//        sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int color=colorValues.getColor(position,Color.BLACK);
//                tv.setTextColor(color);
//            }
//        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int color=colorValues.getColor(position, Color.BLACK);
                tv.setTextColor(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}