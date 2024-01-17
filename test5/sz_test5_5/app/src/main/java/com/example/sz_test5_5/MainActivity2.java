package com.example.sz_test5_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<City> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        lv=findViewById(R.id.listView);
        updateWebData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City item=adapter.getItem(position);
                Intent intent=getIntent();
                Bundle b=new Bundle();
                b.putSerializable(CommonValues.KEY_CITY,item);
                intent.putExtras(b);
                setResult(RESULT_OK,intent);
                finish();

            }
        });

    }

    private void updateWebData() {
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString(CommonValues.KEY_URL);
        WebApiUtils.getApiDataAsync(this, url,
                new WebApiUtils.OnReadFinishedListener() {
                    @Override
                    public void onFinished(List<City> readOutList) {
                        showList(readOutList);
                    }

                    @Override
                    public void onFail(String e) {
                        showToast(e);

                    }
                });


    }

    private void showToast(String e) {
        Toast.makeText(this,e,Toast.LENGTH_LONG).show();
    }

    private void showList(List<City> readOutList) {
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,readOutList);
        lv.setAdapter(adapter);
    }
}
