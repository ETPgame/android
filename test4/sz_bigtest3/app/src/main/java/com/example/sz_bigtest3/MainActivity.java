package com.example.sz_bigtest3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Landscape> list = new ArrayList<>();
    ListView lv ;
    LandscapeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        lv =  findViewById(R.id.listView);
        adapter = new LandscapeAdapter(this,list);
        lv.setAdapter(adapter);
        list.add(new Landscape("Daluo Mountain", R.drawable.da_luo_shan));
        list.add(new Landscape("Qianjiang CBD", R.drawable.qian_jiang_cbd));
        registerForContextMenu(lv);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_add:
                addData();
                break;
            case R.id.opt_reset:
                reset();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.ctx_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
        final int position = adapterContextMenuInfo.position;
        switch (item.getItemId()){
            case R.id.ctx_add:
                addData();
                break;
            case R.id.ctx_modify:
                modify(position);
                break;
            case R.id.ctx_delete:
                list.remove(position);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void modify(int position) {
        final Landscape landscape = list.get(position);
        new Dialog(this,"Modify Data").showDialog(landscape, new Dialog.OnDialogSubmitListener() {
            @Override
            public void onSubmit(Landscape updatedLandscape) {
                landscape.setName(updatedLandscape.getName());
                landscape.setImg(updatedLandscape.getImg());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void reset(){
        list.clear();
        list.add(new Landscape("Daluo Mountain", R.drawable.da_luo_shan));
        list.add(new Landscape("Qianjiang CBD", R.drawable.qian_jiang_cbd));
        adapter.notifyDataSetChanged();
    }
    public void addData(){
        new Dialog(this,"Create Data").showDialog(null, new Dialog.OnDialogSubmitListener() {
            @Override
            public void onSubmit(Landscape updatedLandscape) {
                list.add(updatedLandscape);
                adapter.notifyDataSetChanged();
            }
        });
    }
}