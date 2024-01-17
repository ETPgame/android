package com.example.sz_test4_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        for (int i=0;i<20;i++){
            String s=String.format("Item%02d", i);
            list.add(s);
        }
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        ListView lv=findViewById(R.id.listView);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){

        ContextMenu.ContextMenuInfo menuInfo=item.getMenuInfo();
        
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo=
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position=adapterContextMenuInfo.position;
        
        switch (item.getItemId()){
            case R.id.ctx_insert:
                String s=String.format("Random:%d", new Random().nextInt(1000));
                list.add(position,s);
                adapter.notifyDataSetChanged();
                break;
            case R.id.ctx_delete:
                list.remove(position);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}

