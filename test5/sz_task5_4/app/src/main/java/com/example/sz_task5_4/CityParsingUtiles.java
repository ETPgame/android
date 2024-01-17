package com.example.sz_task5_4;

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

    public static List<City> json2ListByJsonObj(String s) throws JSONException{
        List<City> list=new ArrayList<>();
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

                City city=new City(name,id,weatherid);
                list.add(city);

            }

        }

        return list;


    }

    public static List<City> json2ListByGson(String s) throws JSONException{
        List<City> list=new ArrayList<>();
        if (!TextUtils.isEmpty(s)){
            JSONArray jsonArray=new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                String s1=jsonArray.get(i).toString();
                City city=new Gson().fromJson(s1,City.class);
                list.add(city);
            }

        }
        return list;


    }

    public static List<City> json2ListByGsonList(String s) throws Exception{
        List<City> list=new ArrayList<>();

        if (!TextUtils.isEmpty(s)){
            Type type=new TypeToken<List<City>>(){}.getType();
            list=new Gson().fromJson(s,type);
        }
        return list;

    }

}
