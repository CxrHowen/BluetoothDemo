package com.chen.bluetoothdemo;

import android.app.Application;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG, "Bluetooth");
    }
}
