package com.example.sz_test5_7;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<BookItem>> bookList;
    private MutableLiveData<String> errMessage;

    public MainViewModel() {
        bookList=new MutableLiveData<>();
        errMessage=new MutableLiveData<>();
        bookList.postValue(new ArrayList<>());
    }

    public MutableLiveData<List<BookItem>> getBookList() {
        return bookList;
    }

    public MutableLiveData<String> getErrMessage() {
        return errMessage;
    }
}
