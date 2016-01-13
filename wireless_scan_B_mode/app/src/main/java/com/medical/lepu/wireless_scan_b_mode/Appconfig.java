package com.medical.lepu.wireless_scan_b_mode;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by wong on 16/1/13.
 */
public class Appconfig {


     private   static    final     String     APP_CONFIG   =     "config" ;

     public     static   final     String     APP_UUID     =    "2016_11";

     public    static    final     String    APP_FIRST_START  =   "first_start";


     public    static   final     String     APP_USE_TIMES   =    "use_time";


     public   static   final    String    DEFAULT_SAVE_IMAGE_PATH  = Environment.getExternalStorageDirectory()
             + File.separator+"lepu_utrasound"+File.separator+"img"+File.separator  ;


     public   static    final     String    DEFAULT_SAVE_VIDEO_PATH =  Environment.getExternalStorageDirectory()
             +File.separator+"lepu_utrasound"+File.separator+"video"+File.separator  ;


      private Context                      _context  ;

      private    static    Appconfig     _appconfig   ;




       public   static     Appconfig     get_appconfig   ( Context  context)   {

               if  (_appconfig==null)  {

                     _appconfig   =   new Appconfig()   ;

                   _appconfig._context   =   context    ;

               }

                 return   _appconfig   ;

       }




      public  SharedPreferences    get_default_share_pref    (Context  context)     {


             return PreferenceManager.getDefaultSharedPreferences(context);


      }



       public Properties     get_property    ()   {

           FileInputStream     fis   =   null  ;

            Properties     properties   =   new Properties()  ;

             try   {
                     File    dir_config  =   _context.getDir(APP_CONFIG,Context.MODE_PRIVATE) ;

                     fis   =  new FileInputStream(dir_config.getPath()+File.separator+APP_CONFIG);

                     properties.load(fis);


             }  catch (Exception  e  )  {


             } finally {

                 try {
                        fis.close();

                 }  catch (  Exception  e )
                 {

                 }


             }

             return      properties   ;


       }



    public    String      getProperValue   (String   key)   {


               Properties    properties   =    get_property()  ;

               return   (properties !=null)  ?   properties.getProperty(key):null  ;


    }



     public     void    set_property    (Properties   property)   {

         FileOutputStream    fos   =  null  ;


           try  {

                     File    dir_config    =    _context.getDir(APP_CONFIG,Context.MODE_PRIVATE);

                     File     config_file  =    new File(dir_config,APP_CONFIG) ;

                     fos   =   new FileOutputStream(config_file) ;

                     property.store(fos,null);

                     fos.flush();

           } catch (Exception  e)  {

                 e.printStackTrace();


           }   finally {

               try {
                   fos.close();
               } catch (Exception e) {


               }


           }


     }


       public    void    set  (Properties    ps) {

                  Properties    prop   =   get_property() ;

                      prop.putAll(ps);

                       set_property(prop);

       }



       public    void      set_property_value   (String  key  ,  String  value)  {

                      Properties    props   =  get_property()  ;

                      props.setProperty(key, value) ;

                        set_property(props);

       }



         public    void   remove_property   (String...  key)  {

                 Properties    props   =    get_property()  ;

                 for (String i  :key) {

                        props.remove(i) ;
                 }

                  set_property(props);
         }
























}
