package com.medical.lepu.wireless_scan_b_mode;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by wong on 16/1/13.
 */
public class AppManager {

    private   static Stack<Activity>   activityStack  ;

    private    static   AppManager       appManager   ;

     private    AppManager  ()  {


     }


      public   static    AppManager   getIntance ()  {

                 appManager     =   new AppManager()  ;

               return     appManager  ;

      }


    public   void   addActivity  (Activity   activity)  {


            if ( activityStack==null)  {
                activityStack   =  new Stack<Activity>() ;
            }

              activityStack.add(activity) ;
    }


    public    Activity    getCurrentActivity   ()   {

            return     activityStack.lastElement() ;
    }


    public    void    finishCurrentActivity  ()   {

             Activity   activity    =    activityStack.lastElement()  ;

               finishActivity(activity);

    }


    public    void     finishActivity (Activity  activity)  {

             if  ( activity!=null  && !activity.isFinishing())  {

                      activityStack.remove(activity) ;

                      activity.finish();

                      activity  = null  ;

             }
    }



       public    void    finishActivity    ( Class<?>  cls)   {

             for   (Activity  act  :  activityStack) {

                      if (act.getClass().equals(cls))  {

                             finishActivity(act);

                              break;
                      }



             }



       }


       public    Activity     getActivity  (Class<?> cls)  {

                if  (activityStack  != null) {

                    for (Activity  act  :  activityStack) {

                         if (act.getClass().equals(cls))  {

                              return     act  ;

                         }

                    }


                }

                     return    null  ;


       }




         public    void    finishAllActivity   ()  {

             for (int  i  =0,size=activityStack.size();i<size;i++) {
                  if  (activityStack.get(i)!=null) {

                      activityStack.get(i).finish();
                  }


             }
             activityStack.clear();

         }


          public    void     appExit  (Context   context)  {

                try  {
                     finishAllActivity();

                     android.os.Process.killProcess(android.os.Process.myPid());

                       System.exit(0);


                }  catch (Exception  e  ) {

                }



          }







}
