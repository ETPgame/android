package com.example.sz_task5_4;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


public class City {
    public String name;
    public int id;
    @SerializedName("weather_id")
    public String weatherid;

    @NonNull
    @Override
    public String toString() {
        return String.format("%s,id=%d",name,id);
    }

    public City(String name, int id, String weatherid) {
        this.name = name;
        this.id = id;
        this.weatherid = weatherid;
    }
}
