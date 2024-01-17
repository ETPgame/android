package com.example.sz_bigtest3;

import java.util.ArrayList;

public class LandscapeDateCenter {
    private static ArrayList<Landscape> list;
    public static ArrayList<Landscape> getData(){
        if(list != null){
            return list;
        } else {
            list = new ArrayList<>();
            list.add(new Landscape("Daluo Mountain", R.drawable.da_luo_shan));
            list.add(new Landscape("Great Wall", R.drawable.great_wall));
            list.add(new Landscape("Lingyin Temple", R.drawable.ling_yin_temple));
            list.add(new Landscape("Nanxi River", R.drawable.nan_xi_river));
            list.add(new Landscape("Olympic Sports Center", R.drawable.olympic_sports_center));
            list.add(new Landscape("Qianjinag CBD", R.drawable.qian_jiang_cbd));
//            list.add(new Landscape("The Imperial Palace", R.drawable.the_imperial_palace));
            list.add(new Landscape("West Lake", R.drawable.west_lake));
            list.add(new Landscape("Yandang Mountain", R.drawable.yan_dang_shan));
            return list;
        }
    }
}
