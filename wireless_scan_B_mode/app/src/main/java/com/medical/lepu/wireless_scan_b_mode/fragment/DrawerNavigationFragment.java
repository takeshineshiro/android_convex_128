package com.medical.lepu.wireless_scan_b_mode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.medical.lepu.wireless_scan_b_mode.R;
import com.medical.lepu.wireless_scan_b_mode.base.BaseFragment;
import com.medical.lepu.wireless_scan_b_mode.util.UIHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wong on 16/1/15.
 */
public class DrawerNavigationFragment  extends BaseFragment  implements View.OnClickListener {

    /**
     * Remember the position of the selected item.
     */

    private    static    final   String   CURRENT_SELECTED_POSITION   =  "current_selected_position" ;

    /**
     * A pointer to the current callbacks instance (the Activity).
     */

    private     NavigationDrawerCallbacks      callbacks  ;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */

     private ActionBarDrawerToggle            drawerToggle ;


    private DrawerLayout                      drawerLayout ;


    private   View                            drawerListView ;


    private    View                           fragmentContainerView ;


    @Bind(R.id.menu_item_image)

    View     utrasound_image       ;


    @Bind(R.id.menu_item_video)

    View     utrasound_video        ;


    @Bind(R.id.menu_item_setting)
    View     utrasound_setting     ;

    @Bind(R.id.menu_item_person)

    View     utrasound_person       ;



    private    int                            current_selected_positon  = 0 ;


    private    boolean                        fromSavedInstanceState  ;





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

         try{
               callbacks   =   (NavigationDrawerCallbacks) context ;

         } catch (ClassCastException  e) {

             throw new    ClassCastException("Activity must implement Callback")  ;
         }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         if(savedInstanceState!=null)  {

             current_selected_positon   =    savedInstanceState.getInt(CURRENT_SELECTED_POSITION);

               fromSavedInstanceState  =  true  ;
         }

             selectItem(current_selected_positon);

    }



    private  void    selectItem(int   positon)  {

           current_selected_positon  = positon  ;

        if (drawerLayout!=null)  {
                drawerLayout.closeDrawer(fragmentContainerView);

        }

        if(callbacks!=null)  {

               callbacks.onNavigationDrawerItemSelected(positon);

        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

              drawerListView   =   inflater.inflate(R.layout.fragment_navigation_drawer,container,false) ;

               drawerListView.setOnClickListener(this);

              ButterKnife.bind(this, drawerListView);

               initView(drawerListView);

              return     drawerListView   ;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

          initData();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        utrasound_image.setOnClickListener(this);
        utrasound_video.setOnClickListener(this);
        utrasound_setting.setOnClickListener(this);
        utrasound_person.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        int   id  =  v.getId() ;

         switch (id)  {

             case    R.id.menu_item_image:

                    UIHelper.showUtrasoundImage(getActivity());

                     break ;
             case   R.id.menu_item_video:

                    UIHelper.showUtrasoundVideo(getActivity());

                    break ;

             case   R.id.menu_item_setting:

                    UIHelper.showUtrasoundSetting(getActivity());

                    break ;

             case  R.id.menu_item_person:

                    UIHelper.showUtrasoundPerson(getActivity());

                    break ;

             default:

                 UIHelper.showUtrasoundImage( getActivity());

                    break;

         }


          drawerLayout.postDelayed(new Runnable() {
              @Override
              public void run() {

                  drawerLayout.closeDrawers();
              }
          }, 600)  ;


    }


    @Override
    public void initData() {

        super.initData();
    }


     public    boolean   isDrawerOpen () {

          return   drawerLayout!=null&&drawerLayout.isDrawerOpen(fragmentContainerView) ;

     }


      public void   openDrawerMenu()  {

            drawerLayout.openDrawer(fragmentContainerView);
      }

    @Override
    public void onDetach() {
        super.onDetach();

        callbacks   = null ;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(CURRENT_SELECTED_POSITION,current_selected_positon);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);


    }


    //inter  interface

    public interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int position);
    }






}
