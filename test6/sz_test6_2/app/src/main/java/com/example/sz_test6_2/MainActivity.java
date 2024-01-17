package com.example.sz_test6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    PhoneDatabase db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        ListView listView=findViewById(R.id.listView);
        db=new PhoneDatabase(this);
        db.open();
        Cursor c =db.queryAll();
        String[] keys = new String[]{PhoneDatabase.KEY_NAME,
                PhoneDatabase.KEY_PHONE, PhoneDatabase.KEY_LOOK_UP};

        int[] into=new int[]{R.id.row_view_tv_name,
                R.id.row_view_tv_phone,R.id.row_view_tv_lookup};

        adapter=new SimpleCursorAdapter(this,R.layout.row_view, c,keys,into);

        listView.setAdapter(adapter);

        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRandomData();
                updateListView();
            }
        });

        findViewById(R.id.bt_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.reset();
                updateListView();
            }
        });

    }

    private void updateListView() {
        adapter.getCursor().requery();
    }

    private void insertRandomData() {
        String s1="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨";
        String s2="甲乙丙丁戊己庚辛一二三四五六七八";
        Random random=new Random();
        int n1= random.nextInt(s1.length());
        int n2=random.nextInt(s2.length());

        String name= String.format("%s%s", s1.charAt(n1), s2.charAt(n2));
        String phone = String.format("%04d", random.nextInt(10000));

        db.insertData(name,phone);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}