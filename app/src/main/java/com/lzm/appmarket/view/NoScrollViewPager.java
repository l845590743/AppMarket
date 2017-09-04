package com.lzm.appmarket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class NoScrollViewPager extends LazyViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否派发
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //          return super.onInterceptTouchEvent(ev);
        return false;//传递给孩子
        //        return true;//走到自身的onTouevent中-->影响:孩子无法接收到事件
    }

    /**
     * 是否消费
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //        return super.onTouchEvent(ev);
        return true;//事件消费-->事件结束
        //                return false;//事件未消费-->往上传递
    }
}
