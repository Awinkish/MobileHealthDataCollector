package com.example.khalif.a514app.Databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.TwentyEightQModel;
import com.google.gson.Gson;

public class TwentyEightDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 11;

    private static final String DATABASE_NAME = "twentyEightDb";

    private static final String TABLE_LOGIN = "login";

    static TwentyEightDb sInstance;

    public TwentyEightDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create an instance of the database
    public static TwentyEightDb getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TwentyEightDb(context);
        }

        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "("
                + Constant.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constant.KEY_RAND + " TEXT,"
                + Constant.KEY_VALUE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(sqLiteDatabase);
    }

    public void save(TwentyEightQModel twentyEightQModel, String rand) {
        Gson gson = new Gson();
        String save = gson.toJson(twentyEightQModel, TwentyEightQModel.class);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.KEY_VALUE, save);
        contentValues.put(Constant.KEY_RAND, rand);

        sqLiteDatabase.insert(TABLE_LOGIN, null, contentValues);
    }

    public TwentyEightQModel getData() {
        TwentyEightQModel twentyEightQModel = new TwentyEightQModel();
        Gson gson = new Gson();

        String getQuery = "SELECT * FROM " + TABLE_LOGIN;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leadsdata = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            twentyEightQModel = gson.fromJson(leadsdata, TwentyEightQModel.class);
        }
        cursor.close();

        return twentyEightQModel;
    }

    //Count number of rows
    public int getRowCount() {
        String countQuery = "SELECT * FROM " + TABLE_LOGIN;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();

        cursor.close();

        return rowCount;
    }

    public void resetTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_LOGIN, null, null);
    }

    public TwentyEightQModel getSpecificData(String search) {
        TwentyEightQModel twentyEightQModel = new TwentyEightQModel();
        Gson gson = new Gson();

        String getQuery = "SELECT * FROM " + TABLE_LOGIN + " WHERE client_rand = " + search;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leadsdata = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            twentyEightQModel = gson.fromJson(leadsdata, TwentyEightQModel.class);
        }
        cursor.close();

        return twentyEightQModel;
    }


}
