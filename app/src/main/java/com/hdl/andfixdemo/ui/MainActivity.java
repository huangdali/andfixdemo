package com.hdl.andfixdemo.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.euler.andfix.patch.PatchManager;
import com.hdl.andfixdemo.R;
import com.hdl.andfixdemo.base.MyAppliecation;
import com.hdl.andfixdemo.bean.PatchBean;
import com.hdl.andfixdemo.utils.PatchHelper;
import com.hdl.andfixdemo.utils.runtimepermissions.PermissionsManager;
import com.hdl.andfixdemo.utils.runtimepermissions.PermissionsResultAction;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;

import hdl.com.myhttputils.CommCallback;
import hdl.com.myhttputils.MyHttpUtils;

public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    private TextView tvLog;
    private String testString = "修复前";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseConfig();//基础配置


        tvContent = (TextView) findViewById(R.id.tv_content);
        tvLog = (TextView) findViewById(R.id.tv_log);
        tvContent.setText("--------------content\n");
        showData();
        tvContent.append("\n提示：" + testString);
    }

    /**
     * 基础配置---1、适配android6.0的手机；2、如果是第一次安装软件,那么判断是否有补丁可以下载
     */
    private void baseConfig() {
        /**
         * 1、请求所有必要的权限----适配android6.0的手机
         */
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
//				Toast.makeText(MainActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
                //Toast.makeText(MainActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 2、如果是第一次安装软件,那么判断是否有补丁可以下载
         */
        SharedPreferences sPreferences = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirstRun = sPreferences.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            sPreferences.edit().putBoolean("isFirstRun", false).commit();
//            new MyHttpUtils()
//                    .url("http://121.42.177.185/andfix/checkpatch.json")
//                    .setJavaBean(PatchBean.class)
//                    .onExecute(new CommCallback<PatchBean>() {
//                        @Override
//                        public void onSucess(PatchBean patchBean) {
//                            if (patchBean.getVersionCode() == PatchHelper.getVersionCode(MainActivity.this)) {
//                                PatchHelper.installPatch(MainActivity.this, patchBean.getPatchUrl());
//                                Log.e("Mainactivity", patchBean.toString());
//                            }
//                        }
//
//                        @Override
//                        public void onFailed(String msg) {
//                            Log.e("MainActivity", msg);//错误消息，不要提示用户
//                        }
//                    });
        }

    }

    /**
     * 显示数据
     */
    private void showData() {
        for (int i = 1; i <=10; i++) {
            tvContent.append(i + "--------------content\n");
        }
    }

    public void onRefresh(View view) {
        tvContent.setText("");
        showData();
        tvContent.append("\n提示: "+testString);
    }

    public void onGetData(View view) {
        new MyHttpUtils()
                .url("http://121.42.177.185/andfix/checkpatch.json")
                .setJavaBean(PatchBean.class)
                .onExecute(new CommCallback<PatchBean>() {
                    @Override
                    public void onSucess(PatchBean patchBean) {
                        Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                        KLog.e("同一个版本吗？===" + (patchBean.getVersionCode() == PatchHelper.getVersionCode(MainActivity.this)));
                        if (patchBean.getVersionCode() == PatchHelper.getVersionCode(MainActivity.this)) {
                            installPatch(MainActivity.this, patchBean.getPatchUrl());
                            Log.e("Mainactivity", patchBean.toString());
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e("MainActivity", msg);//错误消息，不要提示用户
                    }
                });
    }

    /**
     * 下载并安装补丁文件
     *
     * @param url
     */
    public void installPatch(final Context context, String url) {
        tvLog.append("下载地址：" + url);
        tvLog.append("文件名：" + url.substring(url.lastIndexOf("/") + 1));
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
                    tvLog.append("sdcard/andfix/" + file.getName());
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
