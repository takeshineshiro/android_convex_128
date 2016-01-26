package com.medical.lepu.wireless_scan_b_mode.entity;

import java.io.Serializable;

/**
 * Created by wong on 16/1/25.
 */
public class DscIndex   extends   Object  implements Serializable {

    private boolean Bin;

    private double Line;

    private double Pixel;

    private double  PartLine;

    private double  PartPixel;


    public void setBin(boolean position) {

        this.Bin = position;
    }

    public boolean getBin() {


        return this.Bin;

    }


    public void setLine(double  line) {

        this.Line = line;


    }


    public double  getLine() {

        return this.Line;

    }


    public void setPixel(double   pixel) {

          this.Pixel    =   pixel  ;
    }


   public   double    getPixel   ()  {

        return     this.Pixel  ;

   }


   public    void    setPartLine (double  partLine)  {

        this.PartLine    =  partLine  ;

   }


    public    double      getPartLine  ()  {


       return     this.PartLine  ;

    }


   public    void     setPartPixel (double    partPixel)  {


       this.PartPixel   =    partPixel  ;

   }



   public    double   getPartPixel ()   {

      return     this.PartPixel   ;

   }






}
