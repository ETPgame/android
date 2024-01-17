package com.example.sz_test6_1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {
    public static final String KEY_NAME="key_name";
    public static final String KEY_PWD="key_pwd";
    public static final String KEY_CHECK="key_check";

    public static SharedPreferences getShared(Activity activity){
        return activity.getSharedPreferences(activity.getLocalClassName(),
                Context.MODE_PRIVATE);
    }

    public static boolean saveName(Activity activity,String name){
        SharedPreferences shared=getShared(activity);
        SharedPreferences.Editor edit=shared.edit();
        edit.putString(KEY_NAME,name);
        return edit.commit();
    }

    public static boolean savePassword(Activity activity,String pwd){
        SharedPreferences shared=getShared(activity);
        SharedPreferences.Editor edit=shared.edit();
        edit.putString(KEY_PWD,pwd);
        return edit.commit();
    }

    public static boolean saveCheckStatus(Activity activity,boolean isChecked){
        SharedPreferences shared=getShared(activity);
        SharedPreferences.Editor edit=shared.edit();
        edit.putBoolean(KEY_CHECK,isChecked);
        return edit.commit();
    }

    public static String loadName(Activity activity){
        SharedPreferences shared=getShared(activity);
        return shared.getString(KEY_NAME,"");
    }

    public static String loadPassword(Activity activity){
        SharedPreferences shared=getShared(activity);
        return shared.getString(KEY_PWD,"");
    }

    public static boolean loadCheckStatus(Activity activity){
        SharedPreferences shared=getShared(activity);
        return shared.getBoolean(KEY_CHECK,false);
    }


}
