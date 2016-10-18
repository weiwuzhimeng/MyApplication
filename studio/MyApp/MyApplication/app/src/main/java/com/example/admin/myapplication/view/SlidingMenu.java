package com.example.admin.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/10/14.
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;
    private int mMenuWidth;
    //菜单栏的右边距
    private int mMenuRightPadding=80;

    private boolean once;

    /**
     * 未使用自定义属性时 调用此SlidingMenu方法
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //Andorid.util包下的DisplayMetrics类提供了一种关于显示的通用信息，如显示大小，分辨率和字体。
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;

        //将50dp转换为像素值  dp转px
        mMenuRightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources()
                .getDisplayMetrics());
    }

    /**
     * 设置子View的宽和高
     * 设置自己的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once){
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
            mMenuWidth = mMenu.getLayoutParams().width;
            mContent.getLayoutParams().width=mScreenWidth;
            once=true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量将menu隐藏 让content显示在屏幕中
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth,0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:  //监听手指抬起这一事件
                //scrollX为menu隐藏在屏幕左边的宽度
                int scrollX = getScrollX();
                if(scrollX >= mMenuWidth/2){
                    this.smoothScrollTo(mMenuWidth,0);  //左边隐藏的宽度为mMenuWidth,及关闭菜单栏
                }else {
                    this.smoothScrollTo(0,0);   //左边0隐藏，及显示菜单栏
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
