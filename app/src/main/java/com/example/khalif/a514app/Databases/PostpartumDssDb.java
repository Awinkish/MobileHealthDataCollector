package com.example.khalif.a514app.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.google.gson.Gson;

/**
 * Created by Khalif on 7/11/2017.
 */
public class PostpartumDssDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "PostpartumDssDb";

    // Login table name
    private static final String TABLE_LOGIN = "login";

    // Login Table Columns names
    static PostpartumDssDb sInstance;

    public PostpartumDssDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static PostpartumDssDb getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new PostpartumDssDb(context);
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

    public void save(PostpartumQModel dataModel, String rand) {
        Gson gson = new Gson();
        String save = gson.toJson(dataModel, PostpartumQModel.class);
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
    public PostpartumQModel getData() {
        PostpartumQModel dsrDataModel = new PostpartumQModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, PostpartumQModel.class);
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
    public PostpartumQModel getSpecificData(String search) {
        PostpartumQModel dsrDataModel = new PostpartumQModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN + " WHERE client_rand ='"+ search.trim() +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, PostpartumQModel.class);
        }

        cursor.close();

        // db.close();
        // return user
        return dsrDataModel;
    }

}
