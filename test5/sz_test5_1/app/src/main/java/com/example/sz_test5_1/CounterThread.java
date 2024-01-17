package com.example.sz_test5_1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.concurrent.atomic.AtomicBoolean;

public class CounterThread extends Thread{

    private static final String KEY_COUNTER = "key_counter";
    private Handler handler;
    private AtomicBoolean isRunning;

    public CounterThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        isRunning=new AtomicBoolean(true);
        float counter=0.0f;

        while (isRunning.get()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            counter+=0.01f;
            Bundle bundle=new Bundle();

            bundle.putFloat(KEY_COUNTER,counter);
            Message message=handler.obtainMessage();
            message.setData(bundle);
            handler.sendMessage(message);
        }
    }

    public void stopCounter(){
        isRunning.set(false);
    }

    public static float getMessageData(Message message){
        Bundle bundle=message.getData();
        float v=bundle.getFloat(KEY_COUNTER);
        return v;
    }
}
