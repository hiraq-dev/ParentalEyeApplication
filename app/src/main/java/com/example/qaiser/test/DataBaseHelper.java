package com.example.qaiser.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Qaiser on 4/25/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase5.db";
    public static final String TABLE_NAME = "entry";
    public static final String TABLE_NAME2="logtable";
    public static final String TABLE_NAME3="register";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "APP_NAME";
    public static final String COL_3 = "NUMBER";
    public static final String COL_4 = "MSG";
    public static final String COL_5 = "DateTime";
    public static final String COL1 = "id";
    public static final String COL2 = "details";
    public static final String COL3 = "datetime";
    public static final String CO1 = "ID";
    public static final String CO2 = "password";

    public static class classgloball{
        public static boolean flag;



    }

    public static final String Table1="CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,APP_NAME TEXT, NUMBER TEXT,MSG TEXT,details TEXT)";
    public static final String Table2="CREATE TABLE " + TABLE_NAME2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,details TEXT,datetime TEXT )";
    public static final String Table3="CREATE TABLE " + TABLE_NAME3 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,password TEXT )";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table1);
        db.execSQL(Table2);
        db.execSQL(Table3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS table1");
        db.execSQL("DROP TABLE IF EXISTS table2");
        db.execSQL("DROP TABLE IF EXISTS table3");

        onCreate(db);
    }

    public boolean insertData(String appName, String Number,String MSG) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_2, appName);
        contentvalues.put(COL_3, Number);
        contentvalues.put(COL_4, MSG);
      //  contentvalues.put(COL_5, Datetime);
        long result = db.insert(TABLE_NAME, null, contentvalues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean registeradd( String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();

        contentvalues.put(CO2, password);
        //  contentvalues.put(COL_5, Datetime);
        long result = db.insert(TABLE_NAME3, null, contentvalues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }









    public int logdata(String details,String datetime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL2, details );
        contentvalues.put(COL3, datetime );
        long result1 = db.insert(TABLE_NAME2, null, contentvalues);
       return 0;


    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }


    public Cursor registeredData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rst=db.rawQuery("SELECT password FROM "+ TABLE_NAME3 ,null);
        return rst;
    }

    public boolean UpdateData(String id, String appName, String Number, String MSG) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentvalues = new ContentValues();
        //contentvalues.put(COL_1, id);
        contentvalues.put(COL_2, appName);
        contentvalues.put(COL_3, Number);
        contentvalues.put(COL_4, MSG);

        long xx= db.update(TABLE_NAME, contentvalues, "ID=?", new String[]{id});


        if (xx!=0)
        {
           return true;
        }
        else
        {
            return false;
        }

    }

}


