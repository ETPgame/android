package com.example.sz_test5_5;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class City implements Serializable {
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
