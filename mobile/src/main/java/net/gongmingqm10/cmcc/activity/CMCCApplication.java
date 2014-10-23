package net.gongmingqm10.cmcc.activity;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class CMCCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
