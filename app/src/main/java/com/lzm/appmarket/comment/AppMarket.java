package com.lzm.appmarket.comment;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by lxj on 2016/5/23.
 */
public class AppMarket extends Application {

    public static Context context;
    public static Handler mainHandler;
    /**
     * Android应用的入口函数：
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Context，handler
        //Android3种Context:Activity, Application, Service
        context = this;
        mainHandler = new Handler();
    }
}
