package com.medical.lepu.wireless_scan_b_mode.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medical.lepu.wireless_scan_b_mode.AppContext;
import com.medical.lepu.wireless_scan_b_mode.interf.BaseFragmentInterface;

/**
 * Created by wong on 16/1/13.
 */
public class BaseFragment  extends Fragment   implements  android.view.View.OnClickListener,BaseFragmentInterface{


    protected LayoutInflater    baseInflater  ;


    public AppContext   getAPPlication ()  {


          return  (AppContext) getActivity().getApplication();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                 this.baseInflater   =  inflater  ;

        return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
    }




    protected   int   getLayoutId()  {

            return    0 ;
    }


       protected    View    inflateView(int  resId) {
           return   this.baseInflater.inflate(resId,null) ;

       }




    @Override
    public void initView() {

    }


    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {

    }
}
