package com.example.sz_task5_4;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebApiUtils {
    private static OkHttpClient client;
    private static OkHttpClient getClient(){
        synchronized (WebApiUtils.class){
            if (client==null){
                client=new OkHttpClient();
            }
            return client;
        }
    }

    public interface OnReadFinishedListener{
        public void onFinished(List<City> readOutList);

        public void onFail(String e);
    }
    public static void getApiDataAsync(Activity activity,String url,OnReadFinishedListener l){

        OkHttpClient c=getClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        l.onFail(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                try {
                    String s=response.body().string();
                    List<City> list=CityParsingUtiles.json2ListByGsonList(s);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            l.onFinished(list);
                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            l.onFail(e.toString());
                        }
                    });

                }

            }
        });





    }

    public static List<City> getApiDataBlock(String url) throws Exception{
        OkHttpClient client=getClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call=client.newCall(request);
        Response response=call.execute();
        String s=response.body().string();
        List<City> list=CityParsingUtiles.json2ListByGson(s);
        return list;

    }
}
