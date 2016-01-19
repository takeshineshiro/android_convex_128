package com.medical.lepu.wireless_scan_b_mode.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.medical.lepu.wireless_scan_b_mode.AppManager;
import com.medical.lepu.wireless_scan_b_mode.interf.BaseViewInterface;

/**
 * Created by wong on 16/1/13.
 */
public class BaseActivity   extends ActionBarActivity implements  BaseViewInterface{


    protected  LayoutInflater      baseInflater  ;

    protected   Toolbar            baseToolbar   ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getIntance().addActivity(this);


      if(getLayoutId()!=0)  {

              setContentView(getLayoutId());

      }


         baseInflater   =   getLayoutInflater()   ;





          baseToolbar   =   initToolbar() ;



           initView();


           initData();


    }




    protected     int    getLayoutId()  {

            return   0 ;

      }


    protected     Toolbar    initToolbar  () {

           return   null  ;

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {

    }
}
