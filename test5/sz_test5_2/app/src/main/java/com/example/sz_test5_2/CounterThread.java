package com.example.sz_test5_2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThread extends Thread{
    private static final String KEY_COUNTER = "key_counter";
    private AtomicBoolean isRunning;
    private Activity activity;
    private OnUpdateListener onUpdateListener;
    private float counter;

    public CounterThread(Activity activity) {
        this.activity = activity;
    }

    public interface OnUpdateListener{
        void onUpdate(float counter);
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    @Override
    public void run() {
        isRunning=new AtomicBoolean(true);
        counter = 0.0f;
        while (isRunning.get()){
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            counter+=0.01f;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(onUpdateListener!=null){
                        onUpdateListener.onUpdate(counter);
                    }
                }
            });
        }
    }

    public void stopCounter(){
        isRunning.set(false);
    }

}
