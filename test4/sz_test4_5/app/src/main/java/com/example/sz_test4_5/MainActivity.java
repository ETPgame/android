package com.example.sz_test4_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<PhoneData> list=new ArrayList<>();
    ArrayAdapter<PhoneData> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        ListView lv=findViewById(R.id.listView);

        for (int i=0;i<4;i++){
            list.add(new PhoneData("小a","11111111111"));
            list.add(new PhoneData("小b","22222222222"));
        }
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos=menuInfo.position;
        switch (item.getItemId()){
            case R.id.ctx_add:
                addData(pos);
                break;
            case R.id.ctx_edit:
                modifyData(pos);
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void modifyData(int pos) {
        PhoneData phoneData=list.get(pos);
        new PhoneDataDialog(this,"修改数据")
                .showDialog(phoneData, new PhoneDataDialog.OnSubmitListener() {
                    @Override
                    public void onSubmit(PhoneData updatedData) {
                        phoneData.setPhone(updatedData.getPhone());
                        phoneData.setName(updatedData.getName());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void addData(int pos) {
        new PhoneDataDialog(this,"新增数据")
                .showDialog(null, new PhoneDataDialog.OnSubmitListener() {
                    @Override
                    public void onSubmit(PhoneData updatedData) {
                        list.add(pos,updatedData);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}