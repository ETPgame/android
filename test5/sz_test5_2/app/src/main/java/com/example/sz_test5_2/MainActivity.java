package com.example.sz_test5_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CounterThread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv=findViewById(R.id.tv_result);
        Button bt_start=findViewById(R.id.bt_start);
        Button bt_stop=findViewById(R.id.bt_stop);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_stop.setEnabled(true);
                bt_start.setEnabled(false);
                thread=new CounterThread(MainActivity.this);
                thread.setOnUpdateListener(new CounterThread.OnUpdateListener() {
                    @Override
                    public void onUpdate(float counter) {
                        tv.setText(String.format("%.2f",counter));
                    }
                });
                thread.start();



            }
        });


        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_start.setEnabled(true);
                bt_stop.setEnabled(false);
                thread.stopCounter();

            }
        });

    }
}