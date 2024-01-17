package com.example.sz_bigtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeDataCenter dataCenter;
    TextView tv;
    ImageView iv;
    ListView lv;
    LandscapeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        Spinner sp=findViewById(R.id.spinner);
        lv=findViewById(R.id.listview);
        iv=findViewById(R.id.imageView);
        tv=findViewById(R.id.tv_info);
        dataCenter=new LandScapeDataCenter();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateListViewItem(position);
            }
        });
        ArrayAdapter<String> spAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,dataCenter.getCityList());
        sp.setAdapter(spAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateListView(int position) {
        ArrayList<Landscape> list=dataCenter.updateLandscapeList(position);
        adapter=new LandscapeAdapter(this,list);
        lv.setAdapter(adapter);
        updateListViewItem(0);
    }

    private void updateListViewItem(int position) {
        Landscape landscape=adapter.getItem(position);
        tv.setText(dataCenter.getCurrentCityName()+":"+landscape.getName());
        iv.setImageResource(landscape.getPicId());
    }
}