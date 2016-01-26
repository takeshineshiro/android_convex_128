package com.medical.lepu.wireless_scan_b_mode.util;

import com.medical.lepu.wireless_scan_b_mode.entity.DscIndex;

/**
 * Created by wong on 16/1/14.
 */
public class DSC_Util extends Object{

    public   static   final   int    DSC_WIDTH           = 640  ;

    public   static   final   int    DSC_HEIGHT          =  480 ;

    public  static    final   int   SAMPLE_IN_LINE      =  512 ;

    public   static   final   int   LINE_IN_FRAME       =   256 ;

    public    static   final   int   PROBE_SECTORARRAY  =  0  ;

    public    static   final   int   PROBE_SECTORSCAN   =  1  ;

    public    static    final   int  PROBE_LINEARARRAY  = 2   ;



    public   static  final   double   PI            =     3.14159265359  ;

    private DscIndex  []   dscIndex   =    new  DscIndex[DSC_WIDTH*DSC_HEIGHT]  ;

    private     byte[]    DSC_Image  =     new  byte[DSC_WIDTH*DSC_HEIGHT*4];


    private    int        probelType    ;

    private     int       zoomFigure    ;


    private    static     DSC_Util     dsc_util   ;




     public   static      DSC_Util    newDSCInstance  ()  {


           dsc_util=      new DSC_Util()   ;


           return        dsc_util   ;

     }



   public     boolean      initDSC (  int  probelType ,  int  zoomFigure)   {

               boolean     DSC_done    =  false   ;

              if (this.probelType==probelType&&this.zoomFigure==zoomFigure )   {

                       DSC_done    =  true  ;

                      return    DSC_done  ;
           }


                this.probelType    =   probelType   ;

                this.zoomFigure    =   zoomFigure   ;


                double  sectorAngle   ;


                double   sectorRad     ;

                double    sampleRate  ;

                int        sampleInLine   =   SAMPLE_IN_LINE  ;


                 if  ( PROBE_SECTORSCAN== probelType) {

                         sectorAngle     =    80.0;         //degree
                         sectorRad       =    15.0  ;      //mm
                         sampleRate      =   2222222.0;   //2Mhz

                         switch (zoomFigure)  {

                             case  0:
                                           sampleInLine     =   280  ;

                                           break  ;

                             case 1:
                                             sampleInLine   =   392   ;

                                               break;

                             case 2:
                                             sampleInLine   =   450   ;

                                              break;
                             case 3 :
                                             sampleInLine    =   512  ;

                                              break;

                             default:
                                             sampleInLine    =   512  ;

                         }

                 }

                else   if  (PROBE_SECTORARRAY ==probelType)   {

                             sectorAngle   =   60.0  ;
                              sectorRad    =   60.0  ;

                           switch (zoomFigure){

                               case  0 :
                                                  sampleRate    =   4545454.0    ;

                                                     break;

                               case  1 :
                                                   sampleRate   =     3125000.0  ;

                                                     break  ;

                               case  2:
                                                   sampleRate  =     2500000.0  ;

                                                     break;

                               case  3:
                                                  sampleRate   =      200000.0  ;

                                                     break;

                               default:
                                                   return   false ;

                     }

                 }

                else     {

                         return     false  ;
                 }


                   double         angle                       =      sectorAngle/180  *PI;       //弧度


                   double         scale_in_sample             =    1560/2/sampleRate  *1000.0  ;    // mm  in  one  sample


                   double         length_RR_no_signal_part     =    (1.0-Math.cos(angle/2))*sectorRad  ;   //mm  which  not signal  part  in  top

                  double           scale_in_pixel              =    (length_RR_no_signal_part+scale_in_sample*sampleInLine)/(double)DSC_HEIGHT  ;   //mm   in  one   pixel


                   double          center_x                    =      (double)  (DSC_WIDTH-1)/2.0  ;   // top  center positon   in  pixel  format


                   double          center_y                    =     -Math.cos(angle/2)*sectorRad/scale_in_pixel ;    //top  center positon   in  pixel  format


                  double           angle_quality               =      angle/255;    //弧度分到256根扫描线中



                  int             indexPos    = 0  ;


                  for  ( int   y=0  ;  y<DSC_HEIGHT;y++) {

                     for  (int  x=0 ;  x<DSC_WIDTH/2;x++)   {

                         double     dis_posx    =   x-  center_x  ;        // distance  from  the  center


                         double   dis_posy      =  y- center_y   ;       //distance   from    the center


                         double   dis_angle     =  Math.atan(dis_posx/dis_posy)   ;    // distance  angle  with  the  center


                          if (dis_angle<=-angle/2)     {

                              dscIndex[indexPos].setBin(false);          //不在图像视角区域

                              indexPos++ ;

                               continue;

                          }
                                                                     /*在图像视角区域*/
                          dscIndex[indexPos].setBin(true);

                          double     dis_angle_corr_line      =   (dis_angle-(-angle/2.0))/angle_quality  ;   //delta弧度所对应的扫描线编号

                          dscIndex[indexPos].setLine(dis_angle_corr_line);

                          double    dis_angle_corr_fraction_line   =   (1.0-(dis_angle_corr_line-(double)((int)dis_angle_corr_line))*255.999)  ; //delta弧度所对应的扫描线编号所对应的小数部分

                          dscIndex[indexPos].setPartLine(dis_angle_corr_fraction_line);


                         double      dis_length    =   Math.sqrt(dis_posx*dis_posx+dis_posy*dis_posy)*scale_in_pixel  ;  //delta所对应的深度（mm）

                         double      dis_sample    =    (dis_length-sectorRad)/scale_in_sample  ;  //delta所对应的采样点编号

                            if  (dis_sample <= 0.0 ||dis_sample  >=511.0)  {         //超过采样点数

                                dscIndex[indexPos].setBin(false);

                                   indexPos ++  ;

                                  continue;

                            }


                               dscIndex[indexPos].setPixel(dis_sample);       //delta所对应的采样点编号


                             double      dis_fraction_sample   =   (double)  ((1.0 - (dis_sample - (double)((int)dis_sample)))*255.999)  ;   //delta所对应的采样点编号小数部分


                                dscIndex[indexPos].setPartPixel(dis_fraction_sample);



                                if ( dscIndex[indexPos].getLine()>=255)

                                        dscIndex[indexPos].setBin(false);

                                 if (dscIndex[indexPos].getPixel()>=511)

                                       dscIndex[indexPos].setBin(false);


                                     indexPos  ++  ;


                     }


                  }


                     DSC_done    =  true  ;



                   return    DSC_done  ;


                 }





     public    double    getScaleInPixel   ( int probelType  ,   int  zoomFigure )     {

                   double        sectorAngle    ;

                   double        sectorRad      ;

                   double        sampleRate     ;


                   int        sampleInLine   =   SAMPLE_IN_LINE  ;


                   if ( PROBE_SECTORSCAN==probelType)    {

                               sectorAngle    =    60.0;

                                sectorRad     =    15.0;

                                sampleRate    =    2222222.0 ;

                          switch (zoomFigure)  {

                              case  0  :

                                                   sampleInLine   =    280 ;

                                                    break;

                              case 1   :
                                                   sampleInLine   =   392  ;

                                                    break;


                              case 2 :
                                                   sampleInLine    =  450;

                                                      break;

                              case 3  :
                                                     sampleInLine  =  512 ;


                                                      break;;

                               default:

                          }

                   }

             else  if (PROBE_SECTORARRAY==probelType) {

                       sectorAngle = 80.0;

                       sectorRad = 40.0;

                       switch (zoomFigure) {

                           case 0:
                               sampleRate = 4545454.0;   //  50MHz div 10+1
                               break;
                           case 1:
                               sampleRate = 3125000.0;   //  50MHz div 15+1
                               break;
                           case 2:
                               sampleRate = 2500000.0;   //  50MHz div 19+1
                               break;
                           case 3:
                               sampleRate = 2000000.0;   //  50MHz div 24+1
                               break;
                           default:
                               return -1;
                           break;


                       }

                   }
                   else  {


                        return    -1  ;
                   }



                       double         angle                       =      sectorAngle/180  *PI;       //弧度


                       double         scale_in_sample             =    1560/2/sampleRate  *1000.0  ;    // mm  in  one  sample


                       double         length_RR_no_signal_part     =    (1.0-Math.cos(angle/2))*sectorRad  ;   //mm  which  not signal  part  in  top

                       double           scale_in_pixel              =    (length_RR_no_signal_part+scale_in_sample*sampleInLine)/(double)DSC_HEIGHT  ;   //mm   in  one   pixel


                   return    scale_in_pixel   ;

     }





       public       byte[]       getDSC_Image  (  byte[]  rawdata)  {


                    int     indexPos    =   0;

                    int     imagePos    =   0;

                  for  (int  y=0  ;  y<DSC_HEIGHT; y++)   {

                            imagePos    =   y*DSC_WIDTH*4 ;

                          for (int  x=0; x<DSC_WIDTH/2;x++)  {

                              DscIndex    initIndex   =    dscIndex[indexPos] ;

                                if (initIndex.getBin())   {


                                     int     imagePosTemp  ;


                                    float    top_left ,top_right,  below_left ,  below_right ;

                                    float     left,  right ;

                                    float    gray  ;

                                                                               //左侧
                                    {

                                        imagePosTemp   =    imagePos  +  (x<<2);







                                    }








                                }






                          }





                  }












       }















   }
















































}
