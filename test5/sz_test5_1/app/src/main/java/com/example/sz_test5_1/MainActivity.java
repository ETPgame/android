package com.example.sz_test5_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Handler handler;
    CounterThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv=findViewById(R.id.tv_result);
        Button bt_start=findViewById(R.id.bt_start);
        Button bt_stop=findViewById(R.id.bt_stop);
        handler=new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                float f =CounterThread.getMessageData(msg);
                tv.setText(String.format("%.2f",f));
                return true;
            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_start.setEnabled(false);
                bt_stop.setEnabled(true);
                thread=new CounterThread(handler);
                thread.start();
            }
        });

        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_stop.setEnabled(false);
                bt_start.setEnabled(true);
                thread.stopCounter();
            }
        });
    }
}