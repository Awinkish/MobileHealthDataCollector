package com.example.khalif.a514app.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.google.gson.Gson;

/**
 * Created by Khalif on 7/12/2017.
 */
public class PregnantDssDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 8;

    // Database Name
    private static final String DATABASE_NAME = "PregnantDssDb";

    // Login table name
    private static final String TABLE_LOGIN = "login";

    // Login Table Columns names
    static PregnantDssDb sInstance;

    public PregnantDssDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static PregnantDssDb getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new PregnantDssDb(context);
        }
        return sInstance;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + Constant.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constant.KEY_RAND + " TEXT,"
                + Constant.KEY_VALUE + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        // Create tables again
        onCreate(db);
    }

    public void save(PregnantQModel dataModel, String rand) {
        Gson gson = new Gson();
        String save = gson.toJson(dataModel, PregnantQModel.class);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.KEY_VALUE, save);
        values.put(Constant.KEY_RAND, rand);

        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        // db.close(); // Closing database connection
    }

    /**
     * Getting user data from database
     */
    public PregnantQModel getData() {
        PregnantQModel dsrDataModel = new PregnantQModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, PregnantQModel.class);
        }
        cursor.close();
        // db.close();
        // return user
        return dsrDataModel;
    }


    /**
     * Getting user login status return true if rows are there in table
     */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        // db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * Re crate database Delete all tables and create them again
     */
    public void resetTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        // db.close();
    }

    public PregnantQModel getSpecificData(String search) {
        PregnantQModel dsrDataModel = new PregnantQModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN + " WHERE client_rand ='"+ search.trim() +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, PregnantQModel.class);
        }

        cursor.close();

        // db.close();
        // return user
        return dsrDataModel;
    }

}
