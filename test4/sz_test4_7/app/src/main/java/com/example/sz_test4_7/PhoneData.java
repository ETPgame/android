package com.example.sz_test4_7;

public class PhoneData {
    private String name;
    private String phone;
    private boolean isChecked=false;

    public PhoneData(String name, String phone, boolean isChecked) {
        this.name = name;
        this.phone = phone;
        this.isChecked = isChecked;
    }

    public PhoneData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return name + " " + phone ;
    }
}

