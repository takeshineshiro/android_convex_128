package com.medical.lepu.wireless_scan_b_mode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wong on 16/1/14.
 */
public class DataBaseHelper  extends SQLiteOpenHelper {

        public    static   final    String   DATABASE_NAME    = "lepu_medical";

        public    static    final  String    TABLE_NAME      =  "image_data";

        public    static   final   String    CREATE_TABLE    =  "create  table "+TABLE_NAME+"(_id integer  primary" +
                "key autoincrement, row  integer, column integer" +"time  varchar(10),date varchar(10))" ;












    public    DataBaseHelper  (Context  context)   {

      super(context,DATABASE_NAME,null,1);
    }








    @Override
    public void onCreate(SQLiteDatabase db) {

           db.execSQL(CREATE_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
