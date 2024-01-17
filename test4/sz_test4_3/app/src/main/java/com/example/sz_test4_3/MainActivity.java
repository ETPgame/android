package com.example.sz_test4_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;
    private int ctx_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        for(int i=0;i<20;i++){
            String s=String.format("Item%02d", i);
            list.add(s);
        }
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,list);
        ListView lv=findViewById(R.id.listView);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        TextView tv=findViewById(R.id.tv_result);
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showTextViewPopUpMenu(tv);
                return true;
            }
        });
    }

    private void showTextViewPopUpMenu(TextView tv) {
        PopupMenu popupMenu=new PopupMenu(getApplicationContext(),tv);
        popupMenu.getMenuInflater().inflate(R.menu.ctx_tv_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ctx_tv_16:
                        tv.setTextSize(16.0f);
                        break;
                    case R.id.ctx_tv_24:
                        tv.setTextSize(24.0f);
                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_menu,menu);

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo=
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        ctx_position=adapterContextMenuInfo.position;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ctx_insert:
                String s=String.format("Random:%d", new Random().nextInt(1000));
                list.add(ctx_position, s);
                adapter.notifyDataSetChanged();
                break;
            case R.id.ctx_delete:
                list.remove(ctx_position);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);

    }
}