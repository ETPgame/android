package com.example.sz_test2_7;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        TextView tv=findViewById(R.id.textView);
        findViewById(R.id.bt_red)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv.setTextColor(Color.RED);
                    }
                });
        findViewById(R.id.bt_blue).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv.setTextColor(0xff0000ff);
                    }
                });
        findViewById(R.id.bt_other).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int otherColor = getResources(). getColor(R.color.teal_700);
                        tv.setTextColor(otherColor);
                    }
                });
    }
}