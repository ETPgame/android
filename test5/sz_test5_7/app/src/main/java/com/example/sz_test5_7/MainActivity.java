package com.example.sz_test5_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    String url="https://www.tup.com.cn/booksCenter/new_book.ashx?pageIndex=0&pageSize=15&id=0&jcls=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        TextView tv=findViewById(R.id.tv_result);
        mainViewModel =new ViewModelProvider(this).get(MainViewModel.class);

        MutableLiveData<List<BookItem>> bookList= mainViewModel.getBookList();
        MutableLiveData<String> errMessage= mainViewModel.getErrMessage();

        bookList.observe(this, new Observer<List<BookItem>>() {
            @Override
            public void onChanged(List<BookItem> bookItems) {
                String s=printBookList(bookItems);
                tv.setText(s);
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

    private String printBookList(List<BookItem> bookItems) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < bookItems.size(); i++) {
            BookItem bookItem=bookItems.get(i);
            sb.append("--------------"+i+"--------------\n");
            sb.append(bookItem.toString());
            sb.append("----------------------------------\n");
        }
        return sb.toString();
    }
}