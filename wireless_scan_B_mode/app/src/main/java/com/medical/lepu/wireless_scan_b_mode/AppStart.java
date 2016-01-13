package com.medical.lepu.wireless_scan_b_mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.medical.lepu.wireless_scan_b_mode.ui.MainActivity;

/**
 * Created by wong on 16/1/13.
 */
public class AppStart  extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

             Activity   activity   =   AppManager.getIntance().getActivity(MainActivity.class) ;

               if (activity!=null && !activity.isFinishing())
                     finish();

        View    view   =  View.inflate(this,R.layout.app_start,null)  ;


           setContentView(view);

          //渐变展示启动屏

        AlphaAnimation     alphaAnimation   =    new AlphaAnimation(0.5f,1.0f) ;

          alphaAnimation .setDuration(600);

          view.startAnimation(alphaAnimation);

         alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
             @Override
             public void onAnimationStart(Animation animation) {

             }

             @Override
             public void onAnimationEnd(Animation animation) {

                     redirectTo ();

             }

             @Override
             public void onAnimationRepeat(Animation animation) {

             }
         });








    }

    private   void  redirectTo () {


        Intent    intent   =   new Intent( this,  MainActivity.class)  ;

         startActivity(intent);

           finish();



    }
}
