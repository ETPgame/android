package com.example.sz_test5_3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private MutableLiveData<Float> counter;

    public CounterViewModel() {
        counter=new MutableLiveData<>();
        counter.postValue(0.0f);
    }

    public MutableLiveData<Float> getCounter() {
        return counter;
    }
}
