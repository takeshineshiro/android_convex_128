package com.medical.lepu.wireless_scan_b_mode.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.medical.lepu.wireless_scan_b_mode.R;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wong on 16/1/19.
 */
public class LeftDrawerAdapter   extends BaseAdapter {

     private Context              context  ;

    private LayoutInflater       layoutInflater  ;

     private  int []  submodule_name   = {R.string.utrasound_image,R.string.utrasound_video,R.string.utrasound_setting,R.string.utrasound_person};


     private  int[]     submodule_pic  =  {R.drawable.drawer_menu_icon_image,R.drawable.drawer_menu_icon_video,

        R.drawable.drawer_menu_icon_setting,R.drawable.drawer_menu_icon_person } ;






    public  LeftDrawerAdapter (Context  context)  {

        this.context    = context  ;

        layoutInflater   =  LayoutInflater.from(context);

    }


    static class   ViewHolder {

        @Bind(R.id.submodule_image)
        ImageView        submoduleImage ;

        @Bind(R.id.submodule_title)
        TextView         submoduleTitle ;




        public   ViewHolder(View view)  {

            ButterKnife.bind(this,view);
        }


    }


    @Override
    public int getCount() {
        return submodule_name.length ;
    }


    @Override
    public Object getItem(int position) {
        return    position;
    }


    @Override
    public long getItemId(int position) {
        return    position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

             ViewHolder    holder   =  null    ;


    if (convertView == null || convertView.getTag() == null) {

        convertView = layoutInflater.inflate(
                R.layout.leftdraweradapter, null);

        holder  = new ViewHolder(convertView);

        convertView.setTag(holder);

    } else {

        holder = (ViewHolder) convertView.getTag();
    }

        holder.submoduleImage.setImageBitmap(readBitMap(context,submodule_pic[position]));

        holder.submoduleTitle.setText(submodule_name[position]);



      return     convertView  ;

    }





    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
// 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}