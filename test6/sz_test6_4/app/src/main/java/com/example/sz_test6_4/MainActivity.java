package com.example.sz_test6_4;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    PhoneDatabase db;
    SimpleCursorAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.opt_menu,menu);
        MenuItem item=menu.findItem(R.id.opt_search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Cursor c=db.fuzzyQuery(newText);
                adapter.swapCursor(c);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

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

        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneDatabase.PhoneCursor c=db.queryById(id);
                c.moveToFirst();
                String phone=c.getPhone();
                c.close();
                Intent intent=new Intent(Intent.ACTION_SENDTO, Uri.parse(
                        "smsto:"+phone
                ));
                startActivity(intent);
            }
        });


//        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertRandomData();
//                updateListView();
//            }
//        });
//
//        findViewById(R.id.bt_reset).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db.reset();
//                updateListView();
//            }
//        });

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_add:
                insertRandomData();
                updateListView();
                break;
            case R.id.opt_reset:
                db.reset();
                updateListView();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long id=menuInfo.id;
        switch (item.getItemId()){
            case R.id.ctx_add:
                addData();
                break;
            case R.id.ctx_edit:
                modifyData(id);
                break;
            case R.id.ctx_delete:
                db.deleteData(id);
                updateListView();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void modifyData(long id) {
        PhoneDatabase.PhoneCursor c=db.queryById(id);
        c.moveToFirst();
        String name=c.getName();
        String phone=c.getPhone();
        c.close();
        PhoneDialog dialog=new PhoneDialog(this,"Edit for _id="+id);
        dialog.showDialog(name, phone, new PhoneDialog.OnSubmitLister() {
            @Override
            public void onSubmit(String updatedName, String updatedPhone) {
                db.updateData(updatedName,updatedPhone,id);
                updateListView();
            }
        });

    }

    private void addData() {
        PhoneDialog dialog=new PhoneDialog(this,"Create new data");
        dialog.showDialog("", "", new PhoneDialog.OnSubmitLister() {
            @Override
            public void onSubmit(String updatedName, String updatedPhone) {
                db.insertData(updatedName,updatedPhone);
                updateListView();
            }
        });
    }
}