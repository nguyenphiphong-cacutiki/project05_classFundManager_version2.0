package com.example.project05_classfundmanager.model;

import android.content.Context;
import android.database.Cursor;

import com.example.project05_classfundmanager.myActivity.MainActivity;
import com.example.project05_classfundmanager.myDatabase.FundDatabase;

public class User {
    private FundDatabase fundDatabase;
    private int id;
    public static String user, pass, mName, mClass, userMember, passMember;
    public static long keyDataBase;

    public boolean isUserExists(Context context, String user){
        FundDatabase fundDatabase = new FundDatabase(context);
        Cursor data = fundDatabase.getData("SELECT * FROM User WHERE user = '"+user+"'");
        return data.moveToNext();
    }
    public void insertUserToDatabase(Context context, String user, String pass, String name, String mClass, long
                                     keyDatabase, String userMember, String passMember){
        fundDatabase = new FundDatabase(context);
        fundDatabase.queryData("INSERT INTO User VALUES(null, '"+user+"', '"+pass+"', '"+name+"', " +
                "'"+mClass+"', '"+keyDatabase+"', '"+userMember+"', '"+passMember+"')");
        fundDatabase.close();
    }
}
