package com.example.sz_test3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        ImageView iv=findViewById(R.id.imageView);
        ListView lv=findViewById(R.id.LV);

        String[] cities=new String[]{"杭州","宁波","温州"};
        int[] images= new int[]{R.drawable.hangzhou,R.drawable.ningbo,R.drawable.wenzhou};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,cities);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iv.setImageResource(images[position]);
            }
        });
    }
}