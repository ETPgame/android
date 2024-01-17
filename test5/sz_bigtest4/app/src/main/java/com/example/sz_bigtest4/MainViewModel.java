package com.example.sz_bigtest4;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<NewsItem>> newsList;
    private MutableLiveData<String> errMessage;

    public MainViewModel() {
        newsList = new MutableLiveData<>();
        errMessage = new MutableLiveData<>();
        newsList.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<NewsItem>> getNewsList() {
        return newsList;
    }

    public MutableLiveData<String> getErrMessage() {
        return errMessage;
    }
}
