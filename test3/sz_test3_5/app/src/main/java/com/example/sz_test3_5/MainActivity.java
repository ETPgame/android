package com.example.sz_test3_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_IMAGE="key_image";
    public static final String KEY_NAME="key_name";
    public static final String KEY_PHONE="key_phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        int[] images=new int[]{R.drawable.hangzhou, R.drawable.ningbo, R.drawable.wenzhou};
        String[] cities=new String[]{"杭州","宁波","温州"};
        String[] phones=new String[]{"11111","22222","333333"};
        ListView lv=findViewById(R.id.listview);
        ArrayList<HashMap<String,Object>> list=new ArrayList<>();

        for(int i=0;i<images.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put(KEY_IMAGE,images[i]);
            hashMap.put(KEY_NAME,cities[i]);
            hashMap.put(KEY_PHONE,phones[i]);
            list.add(hashMap);
        }

        String[] from=new String[]{KEY_IMAGE, KEY_NAME, KEY_PHONE};

        int[] to=new int[]{R.id.row_view_iv, R.id.row_tv_name, R.id.row_tv_phone};

        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.row_view,from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> hashMap=list.get(i);
                String city=(String) hashMap.get(KEY_NAME);
                Toast.makeText(getApplicationContext(),city,Toast.LENGTH_SHORT).show();
            }
        });


    }
}