package com.example.sz_test5_9;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_PAGE = 20;
    private static final String KEY_DATA = "key_data";
    SwipeRefreshLayout refreshLayout;
    ArrayList<String> urlList;
    MainViewModel mainViewModel;
    int page_i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);

        mainViewModel =new ViewModelProvider(this).get(MainViewModel.class);

        MutableLiveData<List<BookItem>> bookList= mainViewModel.getBookList();
        MutableLiveData<String> errMessage= mainViewModel.getErrMessage();
        ListView lv=findViewById(R.id.listView);
        SeekBar seekBar=findViewById(R.id.seekBar);

        iniUrlList();
        refreshLayout=findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page_i++;
                if (page_i>MAX_PAGE){
                    page_i=0;
                }
                seekBar.setProgress(page_i);
                updateWebPage();
            }
        });

        seekBar.setMax(MAX_PAGE);
        seekBar.setProgress(page_i);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                page_i=seekBar.getProgress();
                updateWebPage();

            }
        });
        updateWebPage();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookItem item=(BookItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, BookItemActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(KEY_DATA,item);
                intent.putExtra(KEY_DATA,item);
//                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(item.getHref()));
                startActivity(intent);

            }
        });

        bookList.observe(this, new Observer<List<BookItem>>() {
            @Override
            public void onChanged(List<BookItem> bookItems) {
                refreshLayout.setRefreshing(false);
                BookItemAdapter adapter=new BookItemAdapter(MainActivity.this,bookItems);
                lv.setAdapter(adapter);
            }
        });

        errMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                refreshLayout.setRefreshing(false);
                showToast(s);
            }
        });

    }


    public static BookItem getIntentBookItem(Intent intent) {
        Bundle b = intent.getExtras();
        return (BookItem) b.getSerializable(KEY_DATA);
    }


    private void updateWebPage() {
        refreshLayout.setRefreshing(true);
        String url=urlList.get(page_i);
        new BookItemGetThread(mainViewModel,url).start();
    }

    private void iniUrlList() {
        urlList=new ArrayList<>();
        String url_p="https://www.tup.com.cn/booksCenter/new_book.ashx?pageIndex=%d&pageSize=15&id=0&jcls=0";

        for (int i = 0; i <=MAX_PAGE; i++) {
            String url = String.format(url_p, i);
            urlList.add(url);
        }
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}