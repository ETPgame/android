package com.example.sz_test5_4;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        EditText et=findViewById(R.id.et_url);
        lv=findViewById(R.id.listView);
        findViewById(R.id.bt_async)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url=et.getText().toString().trim();
                        WebApiUtils.getApiDataAsync(MainActivity.this, url,
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
                });

        findViewById(R.id.bt_block)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=et.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            List<City> list=WebApiUtils.getApiDataBlock(url);
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showList(list);
                                }
                            });

                        }catch (Exception e){
                            e.printStackTrace();
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.toString());
                                }
                            });
                        }
                    }
                }).start();

            }
        });
    }

    private void showList(List<City> readOutList){
        ArrayAdapter<City> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,readOutList);
        lv.setAdapter(adapter);
    }

    private void showToast(String e){
        Toast.makeText(this,e,Toast.LENGTH_LONG).show();
    }
}