package com.example.sz_test5_9;

import androidx.lifecycle.MutableLiveData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class BookItemGetThread extends Thread{

    private MainViewModel viewModel;
    private String url;
    public static final String BASE_URL="http://www.tup.tsinghua.edu.cn/bookCenter/";

    public BookItemGetThread(MainViewModel viewModel, String url) {
        this.viewModel = viewModel;
        this.url = url;
    }

    @Override
    public void run() {
        MutableLiveData<List<BookItem>> bookList= viewModel.getBookList();
        List<BookItem> list=bookList.getValue();
        list.clear();
        MutableLiveData<String> errMessage= viewModel.getErrMessage();

        try {
            Document doc= Jsoup.connect(url).timeout(10000).get();
            Elements dls=doc.select("dl[class*=product]");

            for (Element dl:dls){
                String href=dl.select("a").first().attr("abs:href");

                String imgSrc=dl.select("img").first().attr("abs:src");
                String title=dl.select("span").first().attr("title");
                String author=dl.select("p").first().text();

                list.add(new BookItem(title,author,href,imgSrc));

            }
            bookList.postValue(list);

        }catch (Exception e){
            e.printStackTrace();
            errMessage.postValue(e.toString());
        }

    }
}
