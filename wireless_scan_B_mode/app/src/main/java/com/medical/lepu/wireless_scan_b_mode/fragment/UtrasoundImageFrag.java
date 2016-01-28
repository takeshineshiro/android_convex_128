package com.medical.lepu.wireless_scan_b_mode.fragment;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.koushikdutta.async.http.SocketIOClient;
import com.medical.lepu.wireless_scan_b_mode.R;
import com.medical.lepu.wireless_scan_b_mode.base.BaseFragment;
import com.medical.lepu.wireless_scan_b_mode.util.DecodeFrameUtil;
import com.medical.lepu.wireless_scan_b_mode.widget.RawImage;
import com.medical.lepu.wireless_scan_b_mode.widget.VerticalSeekBar;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wong on 16/1/19.
 */
public class UtrasoundImageFrag   extends BaseFragment   implements SeekBar.OnSeekBarChangeListener,View.OnTouchListener {

    private   String     ssid      ;                    //SSID字符串

    private   boolean    ssidValid  ;                  //获取的SSID为探头的SSID

    private   boolean    socketConnected ;            //套接字是否连接（三个套接字均已连接时为YES）

    private   boolean    beRunning  ;                // 探头是否在运行中

    private   int       sendState   ;               // 需要发送的运行状态 (-1: 无需要发送状态， 0：冻结状态， 1:运行状态）

    private   int       sendStateCnt ;              //发送状态的等待计数

    private  boolean    haveImage    ;             //图像区是否有图像

    private  boolean    loadedImage  ;            //有图像的情况下也分为两种情况，一种是载入的图像，一种是重建的图像（回复或者在运行中）

    private  boolean    inCineLoop   ;           //是否处于回放状态中

    private  boolean    inFullScreen ;           //是否处于全屏状态

    private  double     gamaValue    ;           //图像的gama校正,可以从PREF文件中读取


    private   int        probeType  ;           //  探头类型信息, 可以从PREF文件中读取

    private    byte      curr_gain  ;          // 探头当前增益

    private   byte       curr_zoom ;           // 探头当前的缩放

    private   byte       send_gain ;          //  需要下发的增益

    private   byte       send_zoom ;         //  需要下发的缩放

    private   int        sales_code;         //  探头的销售区域代码


    private SocketIOClient    socket_data  ;


    private  SocketIOClient   socket_state_control ;


    private  SocketIOClient    socket_receive_state ;


    private    boolean         data_socket_connected ;


    private    boolean         state_socket_connected;


    private    boolean        control_socket_connected;


    private ArrayList          image_buffer_array  ;      //回放图像数据列表


    private Timer               autoLockTimer      ;       //  自动锁屏定时器


    private RawImage               nullImage       ;      // 空白图像

    private   Timer                nullImageTimer  ;


    private  Image                 gradientImage   ;      //  灰度条图像


    private  TextView               labelNote       ;    //提示信息


    private DecodeFrameUtil         decodeFrame     ;


    private  ArrayList               imageData       ;




























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

         initParamSetting()  ;

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

          main_view.setOnClickListener(this);

          app_start_button.setOnClickListener(new ButtonClickListener());

          verticalSeekBar.setOnSeekBarChangeListener(this);

          seekBar.setOnSeekBarChangeListener(this);


          pre_button.setOnClickListener( new ButtonClickListener());


          play_button.setOnClickListener(new ButtonClickListener());


          next_button.setOnClickListener(new ButtonClickListener());


          save_button.setOnClickListener(new ButtonClickListener());


          scanner_button.setOnClickListener(new ButtonClickListener());

         setting_button.setOnClickListener(new ButtonClickListener());


    }


    @Override
    public void initData() {


        super.initData();

          ssid                  =  ""      ;

          ssidValid             =  false   ;

          socketConnected       =  false   ;

          beRunning             =   false  ;

          sendState             =   -1     ;

          sendStateCnt          =   0      ;

          haveImage             =  false   ;

          inCineLoop            =  false   ;

          inFullScreen          =  false   ;

           gamaValue            =  1.3    ;

           probeType            =  0      ;

           curr_gain            =  0      ;

           curr_zoom            =  0      ;

           send_gain            =  0      ;

           send_zoom            =  0      ;

          socket_data           =  null   ;

          socket_receive_state  =  null   ;

          socket_state_control  =  null   ;

           data_socket_connected=  false  ;

         state_socket_connected =  false  ;

        control_socket_connected =  false ;


          image_buffer_array     =   new ArrayList()  ;


            nullImage              =    new RawImage()   ;

            nullImage.probeType    =   this.probeType ;

            nullImage.zoom         =   this.send_zoom  ;

            nullImage.gain         =   this.send_gain  ;

            nullImage.rawData      =    null           ;



























    }




    /*读取参数设置中写入到PREF文件中的图像参数设置*/
   private    void    initParamSetting   ()  {





   }








    /*button  listener  event   */

    private  class    ButtonClickListener     implements  View.OnClickListener

    {

        @Override
        public void onClick (View v){

      }



    }




  /*view   touch  gesture    listener  event  */

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        return false;



    }




     /* seekbar  listener  event   */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }













}
