package com.medical.lepu.wireless_scan_b_mode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by wong on 16/1/20.
 */
public class VerticalSeekBar extends SeekBar {


    public VerticalSeekBar(Context context) {

        super(context);

        if (isInEditMode()) { return; }

    }



    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        if (isInEditMode()) { return; }

    }


    public VerticalSeekBar(Context context, AttributeSet attrs) {

        super(context, attrs);

        if (isInEditMode()) { return; }
    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(h, w, oldh, oldw);

    }




    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(heightMeasureSpec, widthMeasureSpec);

        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());

    }


    protected void onDraw(Canvas c) {

        c.rotate(-90);

        c.translate(-getHeight(),0);


        super.onDraw(c);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled()) {
            return false;
        }


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:

                int i=0;

                i=getMax() - (int) (getMax() * event.getY() / getHeight());

                setProgress(i);

                Log.i("Progress", getProgress() + "");

                onSizeChanged(getWidth(), getHeight(), 0, 0);

                break;


            case MotionEvent.ACTION_CANCEL:

                break;

        }

        return true;

    }





}
