package com.example.khalif.a514app.Databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.BabyModel;
import com.google.gson.Gson;

public class BabyDetailsDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;

    //Database name
    private static final String DATABASE_NAME = "BabyDetailsDb";

    private static final String TABLE = "LogIn";

    // Login Table Columns names
    static BabyDetailsDb sInstance;


    public BabyDetailsDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static BabyDetailsDb getsInstance(Context context){
        if (sInstance==null){
            sInstance = new BabyDetailsDb(context);
        }
        return  sInstance;
    }

    //Creating table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_LOGIN = "CREATE TABLE" + TABLE + "("
            + Constant.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Constant.KEY_RAND + "TEXT,"
                +Constant.KEY_VALUE + "TEXT"+
        ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_LOGIN);
    }

    //Upgrading table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE);

        onCreate(sqLiteDatabase);
    }

    //Inserting values to db
    public  void save(BabyModel dataModel, String rand){
        Gson gson = new Gson();
        String model = gson.toJson(dataModel,BabyModel.class);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.KEY_RAND,rand);
        contentValues.put(Constant.KEY_VALUE,rand);

        sqLiteDatabase.insert(TABLE,null,contentValues);
    }

    //Extracting user data from database
    public BabyModel getData(){
        BabyModel babyModel = new BabyModel();
        Gson gson = new Gson();
        String selectQuery = "SELECT * FROM "+ TABLE;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cusor = sqLiteDatabase.rawQuery(selectQuery,null);

        //Move to first row
        cusor.moveToFirst();

        while (!cusor.isAfterLast()){
            String leads_data = cusor.getString(cusor.getColumnIndex(Constant.KEY_VALUE));
            babyModel = gson.fromJson(leads_data,BabyModel.class);

            cusor.moveToNext();
        }
        return babyModel;
    }

}
