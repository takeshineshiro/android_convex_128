package com.medical.lepu.wireless_scan_b_mode.entity;

import java.io.Serializable;

/**
 * Created by wong on 16/1/25.
 */
public class DscIndex   extends   Object  implements Serializable {

    private boolean Bin;

    private long Line;

    private long Pixel;

    private long PartLine;

    private long PartPixel;


    public void setBin(boolean position) {

        this.Bin = position;
    }

    public boolean getBin() {


        return this.Bin;

    }


    public void setLine(long line) {

        this.Line = line;


    }


    public long getLine() {

        return this.Line;

    }


    public void setPixel( long  pixel) {

          this.Pixel    =   pixel  ;
    }


   public   long    getPixel   ()  {

        return     this.Pixel  ;

   }


   public    void    setPartLine (long  partLine)  {

        this.PartLine    =  partLine  ;

   }


    public    long     getPartLine  ()  {


       return     this.PartLine  ;

    }


   public    void     setPartPixel (long   partPixel)  {


       this.PartPixel   =    partPixel  ;

   }



   public    long    getPartPixel ()   {

      return     this.PartPixel   ;

   }






}
