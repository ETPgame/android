package com.example.sz_test4_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        tv=findViewById(R.id.tv_result);
        String[] hobbies=new String[]{"游泳","跑步","原神"};

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageDialog("这是一个消息对话框");
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemDialog(hobbies);
            }
        });

        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog(hobbies);
            }
        });
    }

    private void showSingleChoiceDialog(String[] hobbies) {
        AlertDialog.Builder bl=new AlertDialog.Builder(this);
        bl.setTitle("单选对话框").setSingleChoiceItems(hobbies, pos,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pos=which;
                    }
                });
        bl.setPositiveButton("OKEY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText(hobbies[pos]);
            }
        });
        bl.setNegativeButton("Cancel",null).show();
    }


    private void showMessageDialog(String s) {
        AlertDialog.Builder bl=new AlertDialog.Builder(this);
        bl.setTitle("消息提示框").setMessage(s);
        bl.setNegativeButton("Cancel",null);
        bl.show();
    }

    private void showItemDialog(String[] hobbies) {
        AlertDialog.Builder bl=new AlertDialog.Builder(this);
        bl.setTitle("列表框");
        bl.setItems(hobbies,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        String hobby=hobbies[which];
                        tv.setText(hobby);
                    }
                });
        bl.setNegativeButton("Cancel",null).show();
    }
}