package com.medical.lepu.wireless_scan_b_mode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.medical.lepu.wireless_scan_b_mode.R;
import com.medical.lepu.wireless_scan_b_mode.base.BaseFragment;
import com.medical.lepu.wireless_scan_b_mode.widget.VerticalSeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wong on 16/1/19.
 */
public class UtrasoundImageFrag   extends BaseFragment {


    @Bind(R.id.main_view)
    View              main_view           ;

    @Bind(R.id.app_start_button)
    ImageButton       app_start_button   ;

    @Bind(R.id.verticalSeekBar)
    VerticalSeekBar    verticalSeekBar ;

   @Bind(R.id.pic_num)
    TextView           picture_num     ;

    @Bind(R.id.seek_bar)
    SeekBar            seekBar         ;

    @Bind(R.id.pre_button)
    ImageButton        pre_button      ;

    @Bind(R.id.play_button)
    ImageButton        play_button     ;

    @Bind(R.id.next_button)
    ImageButton        next_button     ;

    @Bind(R.id.save_button)
    ImageButton        save_button     ;

    @Bind(R.id.scanner_button)
    ImageButton         scanner_button ;

    @Bind(R.id.setting_button)
    ImageButton         setting_button ;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);



       View   view =     inflater.inflate(R.layout.fragment_image_main_layout,container,false) ;


        ButterKnife.bind(this, view);


        initView(view);


       return   view  ;



   }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


           initData();

    }


    @Override
    public void initView(View view) {


        super.initView(view);
    }


    @Override
    public void initData() {


        super.initData();


    }


    @Override
    public void onClick(View v) {



        super.onClick(v);
    }







}
