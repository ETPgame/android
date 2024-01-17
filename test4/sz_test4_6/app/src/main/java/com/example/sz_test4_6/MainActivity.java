package com.example.sz_test4_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MultiChoiceDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv=findViewById(R.id.tv_result);
        String[] hobbies=new String[]{"抽烟","喝酒","烫头","喝哇哈哈"};
        dialog = new MultiChoiceDialog(this,"多选对话框",hobbies);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.showDialog(new MultiChoiceDialog.OnDialogSubmitListener() {
                    @Override
                    public void onSubmit(List<String> selectedItems) {
                        tv.setText(selectedItems.toString());
                    }
                });
            }
        });
    }
}