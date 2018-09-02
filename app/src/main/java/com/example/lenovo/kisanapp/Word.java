package com.example.lenovo.kisanapp;

import android.os.health.PackageHealthStats;

public class Word {

    private String mName;

    private String mPhoneno;


    private String mOtp;
    private String mDate,mTime;


// for messages tab and database information

    public Word(String Name, String Phone_no, String Otp,String time,String date) {
        mName = Name;
        mPhoneno= Phone_no;
        mOtp= Otp;
        mDate = date;
        mTime = time;
    }


//for contacts tab and contact info activity
    public Word(String Name, String Phone_no) {
        mName=Name; mPhoneno = Phone_no;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public String getName() {
        return mName;
    }


    public String getPhone_no() {
        return mPhoneno;
    }


    public String getOtp() {
        return mOtp;
    }


}