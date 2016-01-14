package com.medical.lepu.wireless_scan_b_mode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.medical.lepu.wireless_scan_b_mode.entity.ImageData;

import java.util.List;

/**
 * Created by wong on 16/1/14.
 */
public class SingletonDatabase {

   private    final   DataBaseHelper    dataBaseHelper  ;

   private   static   SingletonDatabase  singletonDatabase;


   private     SingletonDatabase (Context  context)  {
       super();
       dataBaseHelper   =  new DataBaseHelper(context)  ;

   }


    public  static    SingletonDatabase   newInsatnce (Context  context)  {

               if(singletonDatabase==null)

               singletonDatabase  =   new SingletonDatabase(context) ;

               return    singletonDatabase ;

    }


    public    void    insert   (ImageData  imageData)  {


           String   sql   =    "insert  into " +  DataBaseHelper.TABLE_NAME  ;


           sql  +=  "(_id,row,column,time,date)values(?,?,?,?,?)" ;

        SQLiteDatabase   sqlite =   dataBaseHelper.getWritableDatabase() ;

        sqlite.execSQL(sql,new String[]{imageData.getRow()+"",imageData.getColumn()+"",imageData.getTime(),imageData.getDate()});

             sqlite.close();


    }

    public    void    delete  (int  id)  {

             SQLiteDatabase   sqlite  =    dataBaseHelper.getWritableDatabase() ;

             String   sql  =   "delete  from "+DataBaseHelper.TABLE_NAME+"where  _id = ?";

             sqlite.execSQL(sql,new Integer[]{id});

            sqlite.close();


    }




    public   void    update  (ImageData   imageData)  {

        SQLiteDatabase  sqlite  =   dataBaseHelper.getWritableDatabase() ;

        String  sql  =   ("update"+DataBaseHelper.TABLE_NAME+"set row=?, column=? , time=?,date=?"+"where  _id=?")   ;

        sqlite.execSQL(sql,new String[]{ imageData.getRow()+"",imageData.getColumn()+"",imageData.getTime(),imageData.getDate() });

        sqlite.close();


    }



    public List<ImageData>   query()  {

          return    query(" ");

    }


    public    List<ImageData>   query (String  condition) {

        SQLiteDatabase   sqlite  =  dataBaseHelper.getReadableDatabase()  ;









    }





    }













}
