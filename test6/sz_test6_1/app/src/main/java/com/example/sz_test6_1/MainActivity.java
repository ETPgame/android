package com.example.sz_test6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_name,et_pwd;
    CheckBox cb_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        et_name=findViewById(R.id.et_name);
        et_pwd=findViewById(R.id.et_pwd);
        cb_save=findViewById(R.id.cb_save);
        Boolean isSave=SharedUtils.loadCheckStatus(this);
        cb_save.setChecked(isSave);

        if (isSave){
            String name=SharedUtils.loadName(this);
            String pwd=SharedUtils.loadPassword(this);
            et_name.setText(name);
            et_pwd.setText(pwd);
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                showToast("pwd="+et_pwd.getText().toString());
            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        if (cb_save.isChecked()){
            SharedUtils.saveName(this,et_name.getText().toString());
            SharedUtils.savePassword(this,et_pwd.getText().toString());
            SharedUtils.saveCheckStatus(this,true);
        }else {
            SharedUtils.saveName(this,null);
            SharedUtils.savePassword(this,null);
            SharedUtils.saveCheckStatus(this,false);
        }
    }
}