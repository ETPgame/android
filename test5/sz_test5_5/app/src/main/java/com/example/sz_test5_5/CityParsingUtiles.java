package com.example.sz_test5_5;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityParsingUtiles {

    public static List<com.example.sz_test5_5.City> json2ListByJsonObj(String s) throws JSONException{
        List<com.example.sz_test5_5.City> list=new ArrayList<>();
        if(!TextUtils.isEmpty(s)){
            JSONArray jsonArray=new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int id=jsonObject.getInt("id");
                String name=jsonObject.getString("name");
                String weatherid="";
                if(jsonObject.toString().toLowerCase().contains("weather_id")){
                    weatherid=jsonObject.getString("weather_id");

                };

                com.example.sz_test5_5.City city=new com.example.sz_test5_5.City(name,id,weatherid);
                list.add(city);

            }

        }

        return list;


    }

    public static List<com.example.sz_test5_5.City> json2ListByGson(String s) throws JSONException{
        List<com.example.sz_test5_5.City> list=new ArrayList<>();
        if (!TextUtils.isEmpty(s)){
            JSONArray jsonArray=new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                String s1=jsonArray.get(i).toString();
                com.example.sz_test5_5.City city=new Gson().fromJson(s1, com.example.sz_test5_5.City.class);
                list.add(city);
            }

        }
        return list;


    }

    public static List<com.example.sz_test5_5.City> json2ListByGsonList(String s) throws Exception{
        List<com.example.sz_test5_5.City> list=new ArrayList<>();

        if (!TextUtils.isEmpty(s)){
            Type type=new TypeToken<List<com.example.sz_test5_5.City>>(){}.getType();
            list=new Gson().fromJson(s,type);
        }
        return list;

    }

}
