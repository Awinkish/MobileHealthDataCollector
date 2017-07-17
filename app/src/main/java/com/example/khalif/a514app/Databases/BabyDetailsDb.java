package com.example.khalif.a514app.Databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.BabyModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BabyDetailsDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 12;

    //Database name
    private static final String DATABASE_NAME = "BabyDetailsDb";

    private static final String TABLE = "LogIn";

    // Login Table Columns names
    static BabyDetailsDb sInstance;
    private JSONArray dataJson;


    public BabyDetailsDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static BabyDetailsDb getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BabyDetailsDb(context);
        }
        return sInstance;
    }

    //Creating table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE + "("
                + Constant.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constant.KEY_RAND + " TEXT,"
                + Constant.KEY_VALUE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_LOGIN);
    }

    //Upgrading table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);

        onCreate(sqLiteDatabase);
    }

    //Inserting values to db
    public void save(BabyModel dataModel, String rand) {
        Gson gson = new Gson();
        String model = gson.toJson(dataModel, BabyModel.class);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.KEY_RAND, rand);
        contentValues.put(Constant.KEY_VALUE, model);

        sqLiteDatabase.insert(TABLE, null, contentValues);
    }

    //Extracting user data from database
    public BabyModel getData() {
        BabyModel babyModel = new BabyModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT * FROM " + TABLE;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cusor = sqLiteDatabase.rawQuery(selectQuery, null);

        //Move to first row
        cusor.moveToFirst();

        while (!cusor.isAfterLast()) {
            String leads_data = cusor.getString(cusor.getColumnIndex(Constant.KEY_VALUE));
            babyModel = gson.fromJson(leads_data, BabyModel.class);

            cusor.moveToNext();
        }
        return babyModel;
    }

    public JSONArray getDataJson() throws JSONException {
        BabyModel dsrDataModel = new BabyModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();

        Log.d("count", cursor.getCount() + "");

        JSONArray jsonArray = new JSONArray();

        if (cursor.moveToFirst()) {
            do {
                String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
                dsrDataModel = gson.fromJson(leads_data, BabyModel.class);
                String json = gson.toJson(dsrDataModel, BabyModel.class);
                JSONObject jsonObject = new JSONObject(json);
                jsonArray.put(jsonObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return jsonArray;
    }

    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        // db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    public BabyModel getSpecificData(String search) {
        BabyModel dsrDataModel = new BabyModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT  * FROM " + TABLE + " WHERE client_rand ='" + search.trim() + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String leads_data = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            dsrDataModel = gson.fromJson(leads_data, BabyModel.class);
        }

        cursor.close();

        // db.close();
        // return user
        return dsrDataModel;
    }
}
