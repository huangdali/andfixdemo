package com.hdl.andfixdemo.base;

import android.app.Application;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;
import com.hdl.andfixdemo.utils.PatchHelper;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by HDL on 2016/9/8.
 */
public class MyAppliecation extends Application {
    private static MyAppliecation instance;

    public static MyAppliecation getInstance() {
        return instance;
    }

    private PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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


        patchManager = new PatchManager(this);
        patchManager.init(PatchHelper.getVersionCode(this) + "");//current version
        patchManager.loadPatch();
    }

    public PatchManager getPatchManager() {
        return patchManager;
    }
}
