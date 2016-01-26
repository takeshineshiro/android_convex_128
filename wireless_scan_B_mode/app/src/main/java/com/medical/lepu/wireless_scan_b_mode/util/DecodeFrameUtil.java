package com.medical.lepu.wireless_scan_b_mode.util;

/**
 * Created by wong on 16/1/14.
 */
public class DecodeFrameUtil     {

      private    byte  []   decode_image   =   new     byte[512*256]   ;



      private    byte  []   stream_buffer      ;


      private    int          stream_length    ;


      private    int        frame_num          ;


      private     int         line_num         ;


      private  static   DecodeFrameUtil    decodeFrameUtil  ;












     private    DecodeFrameUtil  (  byte[] sockets_src   ,   int  socket_length  )    {

                    this.stream_buffer   =      sockets_src   ;

                    this.stream_length    =   socket_length   ;

     }




       public static DecodeFrameUtil  newInstance( byte[] sockets_src   ,   int  socket_length   ) {

           decodeFrameUtil  =   new    DecodeFrameUtil (sockets_src,socket_length);


           return       decodeFrameUtil  ;
       }








     private    boolean   validFrame(  )   {

              if (stream_length  >= 512*256 ||stream_buffer.length>=512*256)

                  return   true  ;

              else

                  return   false ;

     }



        public     boolean       decode_data ()   {

            boolean     frame_valid    =  false  ;


               if  (validFrame())    {


                 while  (stream_length>=525)  {

                 //查找帧头

                    int   head_shift    =  0  ;

                 for  (head_shift=0;head_shift<=stream_length-8;head_shift++) {

                     if ((stream_buffer[head_shift]==0x5A)&&(stream_buffer[head_shift+1]==0xA5)
                       &&(stream_buffer[head_shift+2]==0xFF)&&(stream_buffer[head_shift+3]==0x00)
                       &&(stream_buffer[head_shift+4]==0x5A)&&(stream_buffer[head_shift+5]==0xA5)
                       &&(stream_buffer[head_shift+6]==0xFF)&&(stream_buffer[head_shift+7]==0x00)) {

                        if (head_shift>0)    {     //有相对位移
                                                  //移掉相对位移
                           for (int temp =0 ;  temp<stream_length-head_shift;temp++)  {

                                stream_buffer[temp]  =  stream_buffer[head_shift+temp];

                           }

                               stream_length   -=   head_shift   ;

                        }

                                break;
                     }


                 }
                                   //找不到帧头,减掉相对位移
                       if (head_shift>=stream_length-8)    {

                           for  (int temp =0  ;  temp <stream_length-head_shift  ;  temp++) {

                                 stream_buffer[temp]   =   stream_buffer[head_shift+temp]   ;
                           }

                              stream_length   =   stream_length  -head_shift   ;

                                 break;

                       }

                         //找到帧头，并且去掉相对位移后
                         if (stream_length>=525)   {

                               byte  current_frame   =   stream_buffer[8]    ;

                               byte  current_line    =   stream_buffer[9]   ;

                                   current_line  |=  ((current_frame&0x01)<<7);   //jizhen dayu 128 ,ouzhne bubian
                                   current_frame    >>=1  ;             //奇偶zhen当做一个

                                  if ((frame_num!=current_frame)||(line_num!=current_line))  {

                                          frame_num   =   current_frame  ;

                                          line_num    =   0 ;

                                  }


                                   if ((frame_num!=current_frame)||(line_num!=current_line))   {

                                           if (stream_length>525)  {

                                            for (int temp=0;temp<stream_length-525;temp++) {

                                               stream_buffer[temp]  =  stream_buffer[temp+525];

                                            }

                                           }

                                        stream_length-=525;

                                   }

                                  else   {

                                        if  (line_num <256)  {


                                            for (int temp =0  ;temp <512;temp++)  {

                                                decode_image[512*line_num+temp]  =  stream_buffer[10+temp]   ;

                                            }


                                            if (stream_length>525&&stream_length<512*256)  {

                                                for (int temp=0;temp<stream_length-525;temp++) {

                                                    stream_buffer[temp]  =  stream_buffer[temp+525];

                                                }

                                                stream_length-=525;
                                            }


                                           if (line_num==255)   {

                                                  frame_num ++ ;

                                                  line_num =0   ;

                                               frame_valid  =  true ;


                                                break;

                                           }

                                           else  {


                                             line_num ++  ;

                                           }






                                            }




                                        }




                                   }




                         }



                 }



                return    frame_valid  ;


               }






       public      byte  []     getDecode_image  ()  {

               if (decode_data()==true)

                  return    this.decode_image  ;

               else

                   return   null ;

       }















     }





















