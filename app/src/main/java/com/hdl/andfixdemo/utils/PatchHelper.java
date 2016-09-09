package com.hdl.andfixdemo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;

/**
 * Created by HDL on 2016/9/9.
 */
public class PatchHelper {
    /**
     * 获取当前app的版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;

        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 下载并安装补丁文件
     *
     * @param url
     */
    public static void installPatch(final Context context, String url) {
        KLog.e("下载地址：" + url);
        KLog.e("文件名：" + url.substring(url.lastIndexOf("/") + 1));
        final PatchManager patchManager = new PatchManager(context);
        final File file = new File(url.substring(url.lastIndexOf("/") + 1));
        new HttpUtils().download(url, "sdcard/andfix/" + file.getName(), new RequestCallBack<File>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                /**
                 * 下载成功之后就开始打补丁---使用下面的方式就不需要重启app了
                 */
                try {
                    KLog.e("sdcard/andfix/" + file.getName());
                    patchManager.addPatch("sdcard/andfix/" + file.getName());
                    patchManager.loadPatch();
//                    Thread.sleep(2000);//等待5s之后删除补丁文件
//                    context.deleteFile(file.getName());
//                    boolean delete = file.delete();
//                    KLog.e("是否删除成功=" + delete);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
//                Toast.makeText(MainActivity.this, "下载失败了", Toast.LENGTH_SHORT).show();
                Log.e("MyLog", "下载失败了");
            }
        });
    }

}
