package com.example.sz_test3_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.crypto.CipherSpi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        ListView lv=findViewById(R.id.listview);
        ArrayList<City> list=new ArrayList<>();
        list.add(new City("温州","",R.drawable.wenzhou));

        for (int i=0;i<3;i++){
            list.add(new City("杭州","684150",R.drawable.hangzhou));
            list.add(new City("宁波","96132546",R.drawable.ningbo));
            list.add(new City("温州",null,R.drawable.wenzhou));
        }
        CityAdapter adapter=new CityAdapter(this,list);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        list.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}