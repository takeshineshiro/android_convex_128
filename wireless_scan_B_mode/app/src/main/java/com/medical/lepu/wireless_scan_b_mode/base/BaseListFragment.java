package com.medical.lepu.wireless_scan_b_mode.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.medical.lepu.wireless_scan_b_mode.entity.Entity;

/**
 * Created by wong on 16/1/13.
 */
public class BaseListFragment  <T extends Entity> extends  BaseFragment  implements
        SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,AbsListView.OnScrollListener {


    protected    SwipeRefreshLayout    swipeRefreshLayout   ;


    protected    ListView               listView           ;


    protected    ListBaseAdapter<T>    listBaseAdapter     ;


    @Override
    protected int getLayoutId() {
        return super.getLayoutId();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

           View   view   =  inflater.inflate(getLayoutId(),container,false) ;

           initView();

          return    view  ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          initData();
    }


    @Override
    public void initView() {
        super.initView();

        swipeRefreshLayout.setOnRefreshListener(this);

        listView.setOnItemClickListener(this);

        listView.setOnScrollListener(this);

        if (listBaseAdapter != null)
            listView.setAdapter(listBaseAdapter);

        else {

            listBaseAdapter = getListAdapter();

            listView.setAdapter(listBaseAdapter);

        }

    }


    protected      ListBaseAdapter<T>  getListAdapter ()  {

              return   null  ;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onRefresh() {

    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
