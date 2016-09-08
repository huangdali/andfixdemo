package com.hdl.andfixdemo;

import android.app.Application;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by HDL on 2016/9/8.
 */
public class MyAppliecation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 极光推送设置
         */
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        Set<String> tag = new HashSet<>();
        /**
         * 统一设置tag为g3box
         */
        tag.add("andfixdemo");
        tag.add("android");
        JPushInterface.setAliasAndTags(getApplicationContext(), null, tag, null);


        PatchManager patchManager = new PatchManager(this);
        patchManager.init(getVersionCode());//current version
    }

    public String getVersionCode() {
        int versionCode = 1;

        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "" + versionCode;
    }
}
