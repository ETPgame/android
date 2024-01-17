package com.example.sz_test5_3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.concurrent.atomic.AtomicBoolean;

public class CountThread extends Thread{
    private ViewModelStoreOwner owner;
    private AtomicBoolean isRunning;
    private MutableLiveData<Float> counter;
    private int mode;
    public static final int MODE_RESTART=0;
    public static final int MODE_RESUME=1;

    public CountThread(ViewModelStoreOwner owner) {
        this.owner = owner;
        mode=MODE_RESTART;
    }

    public CountThread(ViewModelStoreOwner owner, int mode) {
        this.owner = owner;
        this.mode = mode;
    }

    @Override
    public void run() {
        isRunning=new AtomicBoolean(true);
        CounterViewModel counterViewModel=new ViewModelProvider(owner)
                .get(CounterViewModel.class);

        counter=counterViewModel.getCounter();

        if (mode==MODE_RESTART){
            counter.postValue(0.0f);
        }

        while (isRunning.get()){
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            counter.postValue(counter.getValue()+0.01f);
        }

    }
    public void stopCounter(){
        isRunning.set(false);
    }

}
