package com.medical.lepu.wireless_scan_b_mode;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.medical.lepu.wireless_scan_b_mode.base.BaseApplication;
import com.medical.lepu.wireless_scan_b_mode.util.StringUtils;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by wong on 16/1/13.
 */
public class AppContext   extends BaseApplication  {


    private    static    AppContext    appContext   ;


    private    static    int           use_times     ;









    @Override
    public void onCreate() {

        super.onCreate();

         appContext   =    this  ;

         use_times  =  +1  ;


    }

    public   static    AppContext   getInstance   ()   {

                 return        appContext  ;

     }


       public Properties    getProperties  ()  {


           return    Appconfig.get_appconfig(this).get_property();

       }


     public   void    setProperties   (Properties ps)  {

          Appconfig.get_appconfig(this).set(ps);

     }




     public    boolean   containProperty  (String  key)  {

               Properties   ps  =   getProperties() ;

              return   ps.containsKey(key) ;

     }


      public    void   setProperty (String  key , String  value)  {

           Appconfig.get_appconfig(this).set_property_value(key, value);

      }


     public    String   getProperty  (String  key)   {

              return     Appconfig.get_appconfig(this).getProperValue(key) ;

     }


     public    void     removeProperty (String...key)  {

             Appconfig.get_appconfig(this).remove_property(key);

     }


     public    String    getAppId  ()  {

          String    uuid   =   getProperty(Appconfig.APP_UUID) ;

           if  (StringUtils.isEmpty(uuid)) {

                 uuid   = UUID.randomUUID().toString() ;

               setProperty(Appconfig.APP_UUID,uuid);
           }

             return   uuid  ;


     }


          public PackageInfo    getPackageInfo  ()  {

              PackageInfo    info    =  null  ;

              try {

                  info  = getPackageManager().getPackageInfo(getPackageName(),0) ;

              } catch ( NameNotFoundException  e) {
                    e.printStackTrace(System.err);

              }

               if( info ==null)
                       info =  new PackageInfo()  ;

                  return   info  ;

          }



      public    void    clearAppCache  ()  {

            //清除编辑器保存的临时内容
          Properties   prop   =   getProperties() ;

            for (Object  key  : prop.keySet()) {
                String   _key  =   key.toString()  ;

                if (_key.startsWith("temp"))
                    removeProperty(_key);

            }

      }


   public   static    boolean   isMethodCompat ( int  VersionCode)  {

        int   currentversion    =   Build.VERSION.SDK_INT;

           return    currentversion   >=  VersionCode ;

   }


     public    static    boolean    isFirstStart ()  {

             return    getPreferences().getBoolean(Appconfig.APP_FIRST_START ,true) ;

     }


      public    static    void   setFirstStart (boolean  first)  {

             setBoolean(Appconfig.APP_FIRST_START,first);


      }



    public    static     int    getUseTimes  ()  {

             return   getInt(Appconfig.APP_USE_TIMES ,0) ;
    }


     public    static    void     setUseTimes  ()  {



               setInt(Appconfig.APP_USE_TIMES ,use_times);
     }















}
