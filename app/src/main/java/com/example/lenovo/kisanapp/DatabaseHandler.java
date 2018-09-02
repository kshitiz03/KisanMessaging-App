package com.example.lenovo.kisanapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kisan";
    private final String TABLE_NAME ="messages";
    private Context context;
    private URL url;

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }
//creating database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table messages (name varchar(50),contact varchar(15),Otp varchar(6),time varchar(5),date varchar(10))";
        db.execSQL(query);


    }
    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //extract info to database from word object functions
    public void addMessage(Word name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name.getName());
        cv.put("contact",name.getPhone_no());
        cv.put("Otp",name.getOtp());
        cv.put("time",name.getmTime());
        cv.put("date",name.getmDate());
        db.insert(TABLE_NAME,null,cv);
        Log.e("db","insert");
    }
//read info from database and display on messages tab
    public ArrayList<Word> getAllMessages() {
        ArrayList<Word> msgs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToLast();
            Word word = new Word(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            msgs.add(word);
            while (cursor.moveToPrevious()) {
                word = new Word(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                msgs.add(word);
            }


        return msgs;
    }

}

