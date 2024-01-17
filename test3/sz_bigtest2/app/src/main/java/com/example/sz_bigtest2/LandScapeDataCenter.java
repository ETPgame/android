package com.example.sz_bigtest2;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class LandScapeDataCenter {
    private ArrayList<ArrayList<Landscape>> allLandscapeList;
    private ArrayList<Landscape> currentLandscapeList;
    private ArrayList<String> cityList;
    private int currentCityPosition;
    private String currentCityName;
    public LandScapeDataCenter(){
        iniData();
    }

    private void iniData() {
        allLandscapeList=new ArrayList<>();
        cityList=new ArrayList<>();
        ArrayList<Landscape> wenzhou=new ArrayList<>();
        wenzhou.add(new Landscape("Daluo Mountain",R.drawable.da_luo_shan));
        wenzhou.add(new Landscape("Nanxi River",R.drawable.nan_xi_river));
        wenzhou.add(new Landscape("Yandang Mountain",R.drawable.yan_dang_shan));
        insertLandscapeList("Wenzhou",wenzhou);

        ArrayList<Landscape> hangzhou=new ArrayList<>();
        hangzhou.add(new Landscape("West lake",R.drawable.west_lake));
        hangzhou.add(new Landscape("Qianjiang CBD",R.drawable.qian_jiang_cbd));
        hangzhou.add(new Landscape("Lingyin Temple",R.drawable.ling_yin_temple));
        insertLandscapeList("Hangzhou",hangzhou);

        ArrayList<Landscape> beijing=new ArrayList<>();
        beijing.add(new Landscape("The Imperial Palace",R.drawable.the_imperial_palace));
        beijing.add(new Landscape("Great Wall",R.drawable.great_wall));
        beijing.add(new Landscape("Olympic Sports Center",R.drawable.olympic_sports_center));
        insertLandscapeList("Beijing",beijing);

    }

    private void insertLandscapeList(String cityName, ArrayList<Landscape> landscapeList) {
        cityList.add(cityName);
        allLandscapeList.add(landscapeList);
    }
    public ArrayList<Landscape> updateLandscapeList(int position){
        currentCityPosition=position;
        currentLandscapeList=allLandscapeList.get(currentCityPosition);
        currentCityName=cityList.get(currentCityPosition);
        return currentLandscapeList;
    }

    public int getCurrentCityPosition() {
        return currentCityPosition;
    }
    public String getCurrentCityName(){
        return currentCityName;
    }
    public ArrayList<String> getCityList() {
        return cityList;
    }
}

