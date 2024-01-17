package com.example.sz_test4_5;

public class PhoneData {
    private String name;
    private String phone;

    public PhoneData(String name,String phone){
        this.name=name;
        this.phone=phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s",name,phone);
    }
}
