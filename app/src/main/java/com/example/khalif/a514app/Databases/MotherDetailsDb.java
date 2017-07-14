package com.example.khalif.a514app.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.MotherModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MotherDetailsDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "MotherDetailsDb";

    // Login table name
    private static final String TABLE_LOGIN = "login";

    // Login Table Columns names
    static MotherDetailsDb sInstance;

    public MotherDetailsDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MotherDetailsDb getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MotherDetailsDb(context);
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

    public void save(MotherModel dataModel, String rand) {
        Gson gson = new Gson();
        String model = gson.toJson(dataModel, MotherModel.class);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.KEY_RAND, rand);
        values.put(Constant.KEY_VALUE, model);

        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        // db.close(); // Closing database connection
    }

    /**
     * Getting user data from database
     */
    public MotherModel getData() {
        MotherModel dsrDataModel = new MotherModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, MotherModel.class);
            //list.add(name);
            cursor.moveToNext();
        }

        cursor.close();

        // db.close();
        // return user
        return dsrDataModel;
    }

    public JSONArray getDataJson() throws JSONException {
        MotherModel dsrDataModel = new MotherModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();

        Log.d("count", cursor.getCount() + "");

        JSONArray jsonArray = new JSONArray();

        if (cursor.moveToFirst()) {
            do {
                String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
                dsrDataModel = gson.fromJson(leads_data, MotherModel.class);
                String json = gson.toJson(dsrDataModel, MotherModel.class);
                JSONObject jsonObject = new JSONObject(json);
                jsonArray.put(jsonObject);
            } while (cursor.moveToNext());
        }
//        while (!cursor.isAfterLast()) {
//
//            cursor.moveToNext();
//        }

        cursor.close();

        // db.close();
        // return user
        return jsonArray;
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

    public MotherModel getSpecificData(String search) {
        MotherModel dsrDataModel = new MotherModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN + " WHERE client_rand ='"+ search.trim() +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, MotherModel.class);
        }

        cursor.close();

        // db.close();
        // return user
        return dsrDataModel;
    }
}
