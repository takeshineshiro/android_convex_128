package com.medical.lepu.wireless_scan_b_mode.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.medical.lepu.wireless_scan_b_mode.entity.Entity;

/**
 * Created by wong on 16/1/13.
 */
public class BaseListFragment  <T extends Entity> extends  BaseFragment  implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,AbsListView.OnScrollListener {






}
