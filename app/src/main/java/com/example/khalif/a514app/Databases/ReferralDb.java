package com.example.khalif.a514app.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khalif.a514app.Constants.Constant;

/**
 * Created by Khalif on 7/14/2017.
 */
public class ReferralDb extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="ReferralDb";
    private static final int DATABASE_VERSION = 14;

    private static final String TABLE_LOGIN="login";

    static ReferralDb sInstance;


    public ReferralDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static ReferralDb getInstance(Context context){
        if(sInstance==null){
            sInstance = new ReferralDb(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE="CREATE TABLE " + TABLE_LOGIN + "("
                + Constant.KEY_VALUE + " INTEGER PRIMARY KEY AUTOINCREMENT";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
