package com.example.sz_test5_8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    String url="https://www.tup.com.cn/booksCenter/new_book.ashx?pageIndex=0&pageSize=15&id=0&jcls=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        mainViewModel =new ViewModelProvider(this).get(MainViewModel.class);

        MutableLiveData<List<BookItem>> bookList= mainViewModel.getBookList();
        MutableLiveData<String> errMessage= mainViewModel.getErrMessage();
        ListView lv=findViewById(R.id.listView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookItem item=(BookItem) parent.getItemAtPosition(position);
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(item.getHref()));
                startActivity(intent);

            }
        });

        bookList.observe(this, new Observer<List<BookItem>>() {
            @Override
            public void onChanged(List<BookItem> bookItems) {
                BookItemAdapter adapter=new BookItemAdapter(MainActivity.this,bookItems);
                lv.setAdapter(adapter);
            }
        });

        errMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showToast(s);
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BookItemGetThread(mainViewModel,url).start();
            }
        });

    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}