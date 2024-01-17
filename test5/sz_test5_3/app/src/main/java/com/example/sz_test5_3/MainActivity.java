package com.example.sz_test5_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   CountThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        CounterViewModel counterViewModel=new ViewModelProvider(this).get(CounterViewModel.class);
        MutableLiveData<Float> counter= counterViewModel.getCounter();

        TextView tv=findViewById(R.id.tv_result);
        Button bt_start=findViewById(R.id.bt_start);
        Button bt_stop=findViewById(R.id.bt_stop);

        counter.observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                String s=String.format("%.2f",aFloat);
                tv.setText(s);
            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag=bt_start.getText().toString();
                if (tag.equalsIgnoreCase("start")){
                    thread=new CountThread(MainActivity.this);
                    bt_stop.setEnabled(true);
                    bt_start.setText("pause");
                    thread.start();
                }else if (tag.equalsIgnoreCase("pause")){
                    thread.stopCounter();
                    bt_stop.setEnabled(false);
                    bt_start.setText("resume");

                }else {
                    thread=new CountThread(MainActivity.this,CountThread.MODE_RESUME);
                    bt_start.setText("pause");
                    bt_stop.setEnabled(true);
                    thread.start();
                }
            }
        });

        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_start.setText("start");
                bt_stop.setEnabled(false);
                thread.stopCounter();

            }
        });



    }
}