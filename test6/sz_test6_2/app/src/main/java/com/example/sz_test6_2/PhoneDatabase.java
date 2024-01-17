package com.example.sz_test6_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneDatabase {
    private Context context;
    private static final String DB_NAME = "phone.db";
    public static final String TABLE_NAME = "contact";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOOK_UP = "look_up";

    private  int version = 1;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public PhoneDatabase(Context context){
        this.context = context;
        databaseHelper = new DatabaseHelper();
    }

    public void open(){
        if(db == null || !db.isOpen()){
            db = databaseHelper.getWritableDatabase();
        }
    }

    public void close() {
        if(db !=null && db.isOpen()){
            db.close();
        }
    }

    private ContentValues enCodeContentValues(String name,String phone){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_PHONE,phone);
        cv.put(KEY_LOOK_UP,generateLookup(name));
        return cv;
    }

    private String generateLookup(String name){
        return "";
    }

    public Cursor queryAll(){
        String sql = String.format("SELECT * FROM %s",TABLE_NAME);
        Cursor c = db.rawQuery(sql,null);
        return c;
    }

    public long insertData(String name,String phone){
        ContentValues cv = enCodeContentValues(name,phone);
        return db.insert(TABLE_NAME,null,cv);
    }

    public void reset(){
        databaseHelper.reset(db);
    }

    class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(){
            super(context,DB_NAME,null,version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = String.format("create table %s(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s text, %s text, %s text)",TABLE_NAME,KEY_NAME,KEY_PHONE,KEY_LOOK_UP);
            db.execSQL(sql);
            ContentValues cv1 = enCodeContentValues("张三","1234");
            db.insert(TABLE_NAME,null,cv1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            reset(db);
        }
        public void reset(SQLiteDatabase db){
            String sql = String.format("drop table if exists %s",TABLE_NAME);
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
