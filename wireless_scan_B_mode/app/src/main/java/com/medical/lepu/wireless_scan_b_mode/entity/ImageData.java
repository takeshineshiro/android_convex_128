package com.medical.lepu.wireless_scan_b_mode.entity;

import java.io.Serializable;

/**
 * Created by wong on 16/1/14.
 */
public class ImageData   implements Serializable {

    private    int    row   ;

    private   int    column ;

    private   String  time  ;


    private   String  date  ;



    public   void    setRow (  int  row)  {

          this.row   =  row  ;
    }


    public   void   setColumn (int  column) {
          this.column  = column;

    }

    public   void    setTime (String time ) {

          this.time   =  time ;

    }


    public  void    setDate(String  date) {

          this.date   = date  ;
    }




    public   int   getRow()  {

         return   row  ;
    }


    public   int  getColumn () {

        return   column ;
    }


    public   String   getTime () {

        return    time  ;
    }


    public   String   getDate() {

        return    date  ;
    }



}
