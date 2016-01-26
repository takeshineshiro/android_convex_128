package com.medical.lepu.wireless_scan_b_mode.util;

import com.medical.lepu.wireless_scan_b_mode.entity.DscIndex;

/**
 * Created by wong on 16/1/14.
 */
public class DSC_Util extends Object{

    public   static   final   int    DSC_WIDTH      = 640  ;

    public   static   final   int    DSC_HEIGHT     =  480 ;

    public  static    final   int   SAMPLE_IN_LINE  =  512 ;

    public   static   final   int   LINE_IN_FRAME  =   256 ;

    private DscIndex  []   dscIndex   =    new  DscIndex[DSC_WIDTH*DSC_HEIGHT]  ;

    private     byte[]    DSC_Image  =     new  byte[DSC_WIDTH*DSC_HEIGHT*4];


    private    int        probelType    ;

    private     int       zoomIndex     ;











































}
