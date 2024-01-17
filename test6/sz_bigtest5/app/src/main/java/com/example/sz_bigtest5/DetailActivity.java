package com.example.sz_bigtest5;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    PhoneDatabase db;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        TextView tvCreateTime = findViewById(R.id.tv_create_time);
        TextView tvModifyTime = findViewById(R.id.tv_modify_time);
        EditText etTitle = findViewById(R.id.et_title);
        EditText etContent = findViewById(R.id.et_content);

        db = new PhoneDatabase(this);
        db.open();

        Intent intent = getIntent();
        boolean isNewData = MainActivity.getIntentIsNewData(intent);
        if (!isNewData) {
            id = MainActivity.getIntentIdData(intent);
            PhoneDatabase.PhoneCursor phoneCursor = db.queryById(id);
            tvCreateTime.setText(phoneCursor.getCreateTime());
            tvModifyTime.setText(phoneCursor.getModifyTime());
            etTitle.setText(phoneCursor.getTitle());
            etContent.setText(phoneCursor.getContent());
        }
        else{
            // 假设 etTitle 和 etContent 是 EditText 对象，db 是数据库操作对象，isNewData 是一个标识是否为新数据的变量，id 是数据的标识符
            etTitle.setText("");
            etContent.setText("");
        }
        findViewById(R.id.bt_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (!isNewData) {
                    db.updateData(title, content, id);
                } else {
                    db.insertData(title, content);
                }
                // 设置结果并结束当前活动
                setResult(RESULT_OK);
                finish();
            }
        });
// 设置点击监听器
        findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置结果为取消并结束当前活动
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
