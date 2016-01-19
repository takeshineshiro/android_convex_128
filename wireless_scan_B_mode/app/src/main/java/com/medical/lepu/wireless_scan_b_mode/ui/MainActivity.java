package com.medical.lepu.wireless_scan_b_mode.ui;


import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.medical.lepu.wireless_scan_b_mode.AppManager;
import com.medical.lepu.wireless_scan_b_mode.R;
import com.medical.lepu.wireless_scan_b_mode.adapter.LeftDrawerAdapter;
import com.medical.lepu.wireless_scan_b_mode.base.BaseFragment;
import com.medical.lepu.wireless_scan_b_mode.fragment.UtrasoundImageFrag;
import com.medical.lepu.wireless_scan_b_mode.fragment.UtrasoundPersonInfo;
import com.medical.lepu.wireless_scan_b_mode.fragment.UtrasoundSettingParam;
import com.medical.lepu.wireless_scan_b_mode.fragment.UtrasoundVideoFrag;


public  class   MainActivity     extends ActionBarActivity {



     private  DrawerLayout                         drawerLayout ;


     private   ListView                            leftListView ;

       private ActionBarDrawerToggle               actionBarDrawerToggle  ;


       private   CharSequence                       drawerTitle  ;


       private    CharSequence                       title      ;


      private     String[]                           drawer_array  ;


     public   static   final   String    ITEM_POSITION   =  "item_position" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

         initView();

         initData();

        //set  the default  fragment for  the  activity content view  created

         if (savedInstanceState==null)  {

              selectedItem(0);
         }

        AppManager.getIntance().addActivity(this);


    }



    public    void    initView()  {

        drawerLayout   = (DrawerLayout) findViewById(R.id.drawer_layout)   ;

        leftListView   =  (ListView)   findViewById(R.id.left_drawer)     ;


        // set a custom shadow that overlays the main content when the drawer opens

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon


        actionBarDrawerToggle  =   new ActionBarDrawerToggle(
                 this,                           /* host Activity */
                drawerLayout,                   /* DrawerLayout object */
                null,                          /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,         /* "open drawer" description for accessibility */
                R.string.drawer_close        /* "close drawer" description for accessibility */

             )  {

            public void onDrawerOpened(View drawerView) {

                getSupportActionBar().setTitle(drawerTitle);
               // getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();       // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {

               // getActionBar().setTitle(title);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();    // creates call to onPrepareOptionsMenu()
            }


        };


           drawerLayout.setDrawerListener(actionBarDrawerToggle);


        // enable ActionBar app icon to behave as action to toggle nav drawer

           // getActionBar().setHomeButtonEnabled(true);

         //   getActionBar().setDisplayHomeAsUpEnabled(true);

             getSupportActionBar().setHomeButtonEnabled(true);

             getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // set up the drawer's list view with items and click listener


           leftListView.setAdapter(new LeftDrawerAdapter(this));

          leftListView.setOnItemClickListener(new DrawerItemClickListener());


    }



    public    void    initData() {

        drawer_array = getResources().getStringArray(R.array.drawer_submodules);

        drawerTitle = title = getTitle();
    }




      //listenser
       private   class   DrawerItemClickListener   implements   ListView.OnItemClickListener    {


           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               selectedItem(position);


           }
       }




      private  void    selectedItem  (int  position)  {

          BaseFragment  contentFragment   =   null ;


          switch (position)   {

            case    0:

              contentFragment   =   new UtrasoundImageFrag() ;

                 break;


             case   1:

              contentFragment   =   new UtrasoundVideoFrag() ;

                  break;


              case  2:


                  contentFragment   =   new UtrasoundSettingParam() ;

                  break;


              case  3:


                  contentFragment   =   new UtrasoundPersonInfo() ;

                  break;


              default:


                  contentFragment   =   new UtrasoundImageFrag() ;

          }



              Bundle   sendForFragment   =    new Bundle();

              sendForFragment.putInt(ITEM_POSITION, position);

              contentFragment.setArguments(sendForFragment);

              FragmentManager fragmentManager   =   getFragmentManager()  ;

              fragmentManager.beginTransaction().replace(R.id.content_frame,contentFragment).commit();

            // update selected item and title, then close the drawer

               leftListView.setItemChecked(position,true);

               setTitle(drawer_array[position]);

               drawerLayout.closeDrawer(leftListView);

      }



    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
      //  getActionBar().setTitle(title);
          getSupportActionBar().setTitle(title);
    }

    //following  are   menu  operation  creat , prepare,cliked action
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater   menuInflater     =  getMenuInflater()  ;
        menuInflater.inflate(R.menu.main_menu_wifi,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean    draweropen  =  drawerLayout.isDrawerOpen(leftListView)  ;

        menu.findItem(R.id.wifi).setVisible(!draweropen) ;

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.wifi:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.

          actionBarDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }






}





