package com.example.khalif.a514app.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.SevenQModel;
import com.google.gson.Gson;

public class SevenDaysDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 14;

    private static final String DATABASE_NAME = "sevenDaysDb";

    private static final String TABLE_LOGIN = "login";

    static SevenDaysDb sInstance;

    public SevenDaysDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create an instance of the database
    public static SevenDaysDb getInstance(Context context){
        if(sInstance == null){
            sInstance = new SevenDaysDb(context);
        }

        return sInstance;
    }

    //Creating tables
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_LOGIN);

        onCreate(sqLiteDatabase);
    }

    public void save(SevenQModel sevenQModel, String rand){
        Gson gson = new Gson();
        String save = gson.toJson(sevenQModel,SevenQModel.class);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.KEY_VALUE,save);
        contentValues.put(Constant.KEY_RAND,rand);

        sqLiteDatabase.insert(TABLE_LOGIN,null,contentValues);
    }

    public SevenQModel getData(){
        SevenQModel sevenQModel = new SevenQModel();
        Gson gson = new Gson();

        String query = "SELECT * FROM "+ TABLE_LOGIN;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            String leadsData = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            sevenQModel = gson.fromJson(leadsData,SevenQModel.class);
        }

        cursor.close();

        return sevenQModel;
    }

    //Count number of rows
    public int getRowCount(){
        String countQuery = "SELECT * FROM " +TABLE_LOGIN;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
        int rowCount = cursor.getCount();

        cursor.close();

        return  rowCount;
    }

    public void resetTable(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_LOGIN,null,null);
    }

    public SevenQModel getSpecificDetails(String search){
        SevenQModel sevenQModel = new SevenQModel();
        Gson gson = new Gson();

        String getData = "SELECT + FROM " + TABLE_LOGIN +"WHERE client_rand = "+search.trim()+'"';
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getData,null);

        cursor.moveToFirst();
        if(cursor.getCount()>0){
            String leadsData = cursor.getString(cursor.getColumnIndex(Constant.KEY_VALUE));
            sevenQModel = gson.fromJson(leadsData,SevenQModel.class);
        }
        cursor.close();

        return sevenQModel;
    }


}
