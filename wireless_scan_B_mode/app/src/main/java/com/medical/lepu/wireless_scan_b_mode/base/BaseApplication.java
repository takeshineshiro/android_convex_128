package com.medical.lepu.wireless_scan_b_mode.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.medical.lepu.wireless_scan_b_mode.R;

/**
 * Created by wong on 16/1/12.
 */
public class BaseApplication   extends Application {
        
     private   static  String   UTRA_CONFIG_PREF =   "Utrasound_config.pref";

     public    static Context   _context  ;

     public    static Resources  _resource  ;

     private   static String     lastToast = "";

      private static long      lastToastTime;


    @Override
    public void onCreate() {
        super.onCreate();

        _context   =   getApplicationContext() ;

        _resource  =   _context.getResources() ;

    }



    public  static    synchronized    BaseApplication   getContext   ()   {

           return    (BaseApplication) _context  ;

    }

   public    static    Resources    getResource   ()  {

       return       _resource   ;

   }


    public    static   SharedPreferences   getPreferences ()  {

          SharedPreferences  pref   =    getContext().getSharedPreferences(
                  UTRA_CONFIG_PREF,   Context.MODE_MULTI_PROCESS) ;


             return     pref  ;


    }



        public   static   void   setInt(String key ,  int value)   {

           Editor   editor     =  getPreferences().edit();

            editor.putInt(key, value);

            editor.apply();

       }



       public   static   void    setFloat   (String key, float  value)  {
             Editor   editor     =   getPreferences().edit();

              editor.putFloat(key, value) ;

              editor.apply();


       }


       public   static    void    setLong (String key , long  value)  {
               Editor  editor   =  getPreferences().edit();

                editor.putLong(key, value) ;

               editor.apply();



       }





      public   static    void   setBoolean(String key, boolean  value) {

            Editor   editor  =   getPreferences().edit();

             editor.putBoolean(key, value);


              editor.apply();

      }


       public   static    void   setString (String key, String value){
            Editor  editor     =   getPreferences().edit() ;

            editor.putString(key, value);

             editor.apply();
       }



      public   static   int     getInt  (String key, int defvalue)  {

              return   getPreferences().getInt(key, defvalue) ;


      }


      public   static  float    getFloat  (String key, float defvalue)  {

            return    getPreferences().getFloat(key, defvalue) ;


      }


      public   static    long    getLong (String key,  long defvalue) {

           return   getPreferences().getLong(key, defvalue) ;
      }


     public   static     boolean   getBoolean  (String key,  boolean defvalue )  {


         return   getPreferences().getBoolean(key, defvalue);


     }


       public  static   String    getString ( String key,  String defvalue)  {


           return    getPreferences().getString(key, defvalue) ;

       }



       public   static   void     setDisplaySize  ( Activity  activity)   {

           DisplayMetrics   displayMetrics   =   new DisplayMetrics()  ;

           activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

           Editor    editor     =   getPreferences().edit() ;

           editor.putInt("screen_width",displayMetrics.widthPixels);

           editor.putInt("screen_height",displayMetrics.heightPixels) ;

           editor.apply();

       }


       public    static    int[]    getDislaySize   ( int arg0, int  arg1)  {

                   int []  size   =  {};

                   size[0] =  getPreferences().getInt("screen_width",arg0) ;

                   size[1]  = getPreferences().getInt("screen_height",arg1);

               return   size  ;

       }


        public  static    String     getStringResource  ( int  id )  {

             return    _resource.getString(id)  ;

        }


       public   static    String   getStringResource   (int  id , Object...args)  {

           return     _resource.getString(id,args) ;

       }


        public   static    void   showToast  ( int  message)    {

               showToast(message, Toast.LENGTH_LONG,0);
        }


       public    static    void    showToast  (String  message)  {

               showToast(message,Toast.LENGTH_LONG,0, Gravity.BOTTOM);

       }

       public    static    void     showToast  (int message , int  icon)     {

               showToast(message,Toast.LENGTH_LONG,icon);


       }


      public    static   void     showToast   (String message , int icon)   {

                showToast(message , Toast.LENGTH_LONG,icon,Gravity.BOTTOM);

      }

     public   static   void    showToastShort   ( int  message)  {
                   showToast(message,Toast.LENGTH_SHORT,0);

     }

      public   static   void      showToastShort  (String message)    {

                showToast(message,Toast.LENGTH_SHORT,0,Gravity.BOTTOM);

      }

      public    static    void     showToastShort  (int  massage ,Object...args)  {

          showToast(massage,Toast.LENGTH_SHORT,0,Gravity.BOTTOM,args);
      }



    public    static    void    showToast  (int message ,int duration, int  icon)  {

              showToast(message,duration,icon,Gravity.BOTTOM);
    }


     public    static    void    showToast (int message , int duration ,  int  icon, int gravity)   {

             showToast(getContext().getString(message),duration,icon,gravity);

     }

     public    static   void     showToast   (int message , int  duration ,  int  icon ,  int gravity ,Object...args)  {

     showToast(getContext().getString(message,args),duration,icon,gravity);


     }


      //here  need to  change

    public static void showToast(String message, int duration, int icon,
                                 int gravity) {

        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(getContext()).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(getContext());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }






}
