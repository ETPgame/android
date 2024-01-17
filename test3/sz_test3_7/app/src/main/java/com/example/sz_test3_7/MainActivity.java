package com.example.sz_test3_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            String item = String.format("Item%02d", i);
            list.add(item);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,list);
        GridView gv=findViewById(R.id.gridView);
        TextView tv=findViewById(R.id.tv_result);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=list.get(i);
                tv.setText(item);
            }
        });

    }
}