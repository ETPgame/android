package com.example.sz_test4_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<PhoneData> list;
    PhoneDataAdapter adapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        list=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            String phone= String.valueOf(i*111);
            list.add(new PhoneData("小帅"+i,phone));
            list.add(new PhoneData("小美"+i,phone));
        }

        tv=findViewById(R.id.tv_result);
        adapter=new PhoneDataAdapter(this,list);
        ListView lv=findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneData item=adapter.getItem(position);
                Log.d("ListView", "Item clicked at position: " + position);
                tv.setText(item.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.opt_menu_send);
        String title=String.format("发送(%d)",adapter.getCheckedCount());
        item.setTitle(title);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_menu_setAll:
                adapter.setAllChecked();

                break;
            case R.id.opt_menu_clearAll:
                adapter.clearAllChecked();

                break;
            case R.id.opt_menu_reverse:
                adapter.reverseChecked();


                break;
            case R.id.opt_menu_send:
                List<PhoneData> checkedList=adapter.getCheckedList();
                tv.setText(checkedList.toString());


                break;

        }

        return super.onOptionsItemSelected(item);
    }
}