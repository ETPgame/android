package com.example.sz_bigtest2;

public class Landscape {
    private String name;
    private int picId;
    public Landscape(String name,int picId){
        this.name=name;
        this.picId=picId;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
