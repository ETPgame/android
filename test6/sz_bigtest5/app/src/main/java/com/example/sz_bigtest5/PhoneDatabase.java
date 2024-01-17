package com.example.sz_bigtest5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneDatabase {
    private Context context;
    private static final String DB_NAME = "phone.db";
    public static final String TABLE_NAME = "contact";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_LOOK_UP = "look_up";
    public static final String KEY_MODIFY_TIME = "modify_time";
    public static final String KEY_CREATE_TIME = "create_time";
    private int version = 1;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public PhoneDatabase(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper();
    }

    public void open() {
        if (db == null || !db.isOpen()) {
            db = databaseHelper.getWritableDatabase();
        }
    }
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public Cursor queryAll() {
        String sql = String.format("select * from %s", TABLE_NAME);
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor fuzzyQuery(String match) {
        if (TextUtils.isEmpty(match)) {
            return queryAll();
        }
        String sql = String.format("select * from %s where %s like ? or %s like ?", TABLE_NAME, KEY_LOOK_UP, KEY_CONTENT);
        String[] args=new String[]{"%"+match+"%","%"+match+"%"};
        Cursor c = db.rawQuery(sql, args);
        return c;
    }

    public PhoneCursor queryById(long id) {
        String sql = String.format("select * from %s where _id=%d", TABLE_NAME, id);
        Cursor c = db.rawQuery(sql, null);
        PhoneCursor phoneCursor = new PhoneCursor(c);
        phoneCursor.moveToFirst();

        return phoneCursor;
    }
    public long insertData(String title, String content) {
        ContentValues cv = encodeContentValues(title, content);
        String s = (String) cv.get(KEY_MODIFY_TIME);
        cv.put(KEY_CREATE_TIME, s);
        return db.insert(TABLE_NAME, null, cv);
    }

    public long updateData(String title, String content, long id) {
        ContentValues cv = encodeContentValues(title, content);
        return db.update(TABLE_NAME, cv, "_id=" + id, null);
    }

    public void reset() {
        databaseHelper.reset(db);
    }

    class PhoneCursor extends CursorWrapper {
        private Cursor c;

        public PhoneCursor(Cursor cursor) {
            super(cursor);
            this.c = cursor;
        }

        public String getTitle() {
            int idx = c.getColumnIndex(KEY_TITLE);
            return c.getString(idx);
        }

        public String getContent() {
            int idx = c.getColumnIndex(KEY_CONTENT);
            return c.getString(idx);
        }

        public String getCreateTime() {
            int idx = c.getColumnIndex(KEY_CREATE_TIME);
            return c.getString(idx);
        }

        public String getModifyTime() {
            int idx = c.getColumnIndex(KEY_MODIFY_TIME);
            return c.getString(idx);
        }
    }
    class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper() {
            super(context, DB_NAME, null, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = String.format("create table %s (_id integer primary key autoincrement, %s text, %s text, %s text, %s text, %s text)", TABLE_NAME, KEY_TITLE, KEY_CONTENT, KEY_LOOK_UP, KEY_CREATE_TIME, KEY_MODIFY_TIME);
            db.execSQL(sql);
            ContentValues cv = encodeContentValues("周末要完成的作业", "Android 的小实验和大实验");
            String s = (String) cv.get(KEY_MODIFY_TIME);
            cv.put(KEY_CREATE_TIME, s);
            db.insert(TABLE_NAME, null, cv);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            reset(db);
        }

        public void reset(SQLiteDatabase db) {
            String sql = String.format("drop table if exists %s", TABLE_NAME);
            db.execSQL(sql);
            onCreate(db);
        }
    }
    private ContentValues encodeContentValues(String title, String content) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        cv.put(KEY_CONTENT, content);
        cv.put(KEY_LOOK_UP, generateLookup(title));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleDateFormat.format(date);
        cv.put(KEY_MODIFY_TIME, s);
        return cv;
    }

    private String generateLookup(String name) {
        return PinyinUtils.generateLookup(name);
    }



}

