package com.example.sz_bigtest4;

import androidx.lifecycle.MutableLiveData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class NewsItemGetThread extends Thread{
    private MainViewModel viewModel;
    private String url;

    public NewsItemGetThread(MainViewModel viewModel, String url) {
        this.viewModel = viewModel;
        this.url = url;
    }

    @Override
    public void run() {
        MutableLiveData<List<NewsItem>> newsList = viewModel.getNewsList();
        List<NewsItem> list = newsList.getValue();
        list.clear();
        MutableLiveData<String> errMessage = viewModel.getErrMessage();
        try {
            // 爬虫代码，具体分析网页
            System.out.println(url);
            Document doc = Jsoup.connect(url).timeout(1000000).get();
            Elements eles = doc.select("li[id*=line_u8]");
            for (Element el : eles) {
                String title = el.select("a").text();
                String time = el.select("samp").first().text();
                String href = el.select("a").first().attr("abs:href");
                String base_url = href;
                //图片怎么获取,注意避免空指针异常,不包含img时直接设置
                String imgSrc = "https://www.wzu.edu.cn/__local/F/" +
                        "20/52/AD8FDB193A28F183CBEA2162438_D15AE825_7B06.jpg?e=.jpg";

                Document new_doc = Jsoup.connect(base_url).timeout(10000).get();
                Element imgElement = new_doc.select("img[class*=img_vsb_content]").first();
                if(imgElement != null) {
                    imgSrc = imgElement.attr("abs:src");
                    // 接下来可以使用imgSrc变量进行操作
                }
                list.add(new NewsItem(title,time,href,imgSrc));
            }
            newsList.postValue(list);
        } catch (IOException e) {
            e.printStackTrace();
            errMessage.postValue(e.toString());
        }
    }
}
