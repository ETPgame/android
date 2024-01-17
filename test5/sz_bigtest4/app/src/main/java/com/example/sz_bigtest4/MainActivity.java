package com.example.sz_bigtest4;

import android.content.Intent;
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
    public static final String KEY_DATA = "key_data";
    MainViewModel viewModel;
    int MAX_PAGE = 20;
    int page_i = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> urlList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        MutableLiveData<List<NewsItem>> newsList = viewModel.getNewsList();

        ListView lv = findViewById(R.id.listView);
        initUrlList();
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(MAX_PAGE);
        seekBar.setProgress(page_i);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                page_i = seekBar.getProgress();
                updateWebpage();
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page_i ++;
                if (page_i > MAX_PAGE){
                    page_i = 0;
                }
                seekBar.setProgress(page_i);
                updateWebpage();
            }
        });

        updateWebpage();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsItem newsItem = (NewsItem) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, NewsItemActivity.class);
                Bundle b =new Bundle();
                b.putSerializable(KEY_DATA,newsItem);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        newsList.observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                swipeRefreshLayout.setRefreshing(false);
                NewsItemAdapter newsItemAdapter = new NewsItemAdapter(MainActivity.this, newsItems);
                lv.setAdapter(newsItemAdapter);
            }
        });
        MutableLiveData<String> errMessage = viewModel.getErrMessage();
        errMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showToast(s);
            }
        });
    }

    private void updateWebpage() {
        swipeRefreshLayout.setRefreshing(true);
        String url = urlList.get(page_i);
        new NewsItemGetThread(viewModel,url).start();
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }


    private void initUrlList() {
        urlList = new ArrayList<>();
        urlList.add("https://tw.wzu.edu.cn/twxw2.htm");
        String url_p = "https://tw.wzu.edu.cn/twxw2/%d.htm";
        int page_now = 91;

        for (int i = 0; i <MAX_PAGE; i++) {
            String url = String.format(url_p,page_now-i);
            urlList.add(url);
        }
    }
    public static NewsItem getIntentNewsItem(Intent intent) {
        Bundle b = intent.getExtras();
        return (NewsItem) b.getSerializable(KEY_DATA);
    }
}