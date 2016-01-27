package com.medical.lepu.wireless_scan_b_mode.util;

import java.io.Serializable;

/**
 * Created by wong on 16/1/25.
 */
public class Gama_Util   extends Object  implements Serializable{

       private     double     gama    =  0.45 ;


      private   static      Gama_Util     gama_util   ;


      public     int    []   gama_table  =    new  int [256];        //伽马查找表



     public static Gama_Util newInstance(  ) {


            gama_util    =   new Gama_Util()  ;

            return    gama_util  ;

    }




    public    boolean     initGama ( double   gama_in  )   {

               if (gama_in!=0)

                  this.gama    =  gama_in  ;

             boolean     initDone   =  false  ;


              if (this.gama<0.1 ||this.gama  >4.0)

                    return    initDone  ;


              final   double    e   =   -0.05  ;


                for  (int  i =0  ;i<256;i++)   {

                      double    x =   (double)   i/255  ;

                      double    y =    Math.pow(x + e, gama) ;


                       if (y<0)

                                y  = 0  ;

                        if(y>1)

                               y  =1  ;


                            y*=255*256;


                    gama_table[i]  =   (int ) y;

                }

                    initDone   =  true  ;

                  return    initDone   ;



    }












          



}
