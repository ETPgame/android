package com.example.sz_bigtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class sz_MainActivity extends AppCompatActivity {

    CheckBox cb1,cb2,cb3;
    TextView tv_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sz_main);

//        editText文本获取
        EditText et=findViewById(R.id.ed1);
        TextView tv_ed=findViewById(R.id.tv_result1);
        findViewById(R.id.bt_get_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_ed.setText(et.getText().toString());
            }
        });


//        radio_group单选框
        RadioGroup rg=findViewById(R.id.radio_group);
        TextView tv_bt=findViewById(R.id.tv_bt);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=findViewById(checkedId);
                tv_bt.setText(rb.getText());
            }
        });


//        checkBox操作
        tv_cb=findViewById(R.id.tv_cb);
        cb1=findViewById(R.id.cb_red);
        cb2=findViewById(R.id.cb_blue);
        cb3=findViewById(R.id.cb_green);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateCheckBox();
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateCheckBox();
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateCheckBox();
            }
        });



    }

    private void UpdateCheckBox() {
        String s="";
        if(cb1.isChecked()){
            s += cb1.getText() + " ";
        }
        if(cb2.isChecked()){
            s += cb2.getText() + " ";
        }
        if(cb3.isChecked()){
            s += cb3.getText();
        }
        tv_cb.setText(s);
    }


}