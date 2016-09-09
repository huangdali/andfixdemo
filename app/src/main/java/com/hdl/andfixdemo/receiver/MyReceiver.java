package com.hdl.andfixdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.hdl.andfixdemo.base.MyAppliecation;
import com.hdl.andfixdemo.utils.PatchHelper;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;


/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        Bundle bundle = intent.getExtras();
        Log.e(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.e(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.e(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.e(TAG, "[MyReceiver] 用户点击打开了通知");

            //打开自定义的Activity
//        	Intent i = new Intent(context, TestActivity.class);
//        	i.putExtras(bundle);
//        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        	context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.e(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.e(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.e(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    //**************下面是获取附加字段versionCode和url的处理(用户不可见哦)*************start***************
                    String versionCode = "";
                    String url = "";
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                        if ("version".equals(myKey)) {
                            versionCode = json.optString(myKey);


                        } else if ("url".equals(myKey)) {
                            url = json.optString(myKey);
                        }
                    }
                    KLog.e("版本号：" + versionCode);
                    KLog.e("下载地址:" + url);
                    if (PatchHelper.getVersionCode(context) == Integer.parseInt(versionCode)) {

//                        PatchHelper.installPatch(context, url);//下载并安装补丁文件
                        installPatch(url);
                    }
                    //**************上面是获取附加字段versionCode和url的处理*************end***************
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        Log.e("MyReceiver", "发送消息给activity");
    }

    /**
     * 下载并安装补丁文件
     *
     * @param url
     */
    public void installPatch(final String url) {
        KLog.e("下载地址：" + url);
        KLog.e("文件名：" + url.substring(url.lastIndexOf("/") + 1));
//        final PatchManager patchManager = new PatchManager(context);
        final File file = new File(url.substring(url.lastIndexOf("/") + 1));
        boolean flag = true;
        for (int i = 1; i < 3; i++) {
            if (flag) {
                flag = false;
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
                            MyAppliecation.getInstance().getPatchManager().removeAllPatch();//将之前的全部删除即可
                            MyAppliecation.getInstance().getPatchManager().addPatch("sdcard/andfix/" + file.getName());
//                    MyAppliecation.getInstance().getPatchManager().loadPatch();
//                    Thread.sleep(2000);//等待5s之后删除补丁文件
//                    boolean deleteFile = context.deleteFile(file.getName());
                            FileOutputStream fos = new FileOutputStream("sdcard/andfix/text.txt", true);
                            fos.write(("文件名：" + url.substring(url.lastIndexOf("/") + 1)).getBytes("UTF-8"));
                            fos.flush();
                            fos.close();
//                    file.renameTo(new File("ceshi.apatch"));
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
            } else {
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
//                            MyAppliecation.getInstance().getPatchManager().removeAllPatch();//将之前的全部删除即可
                            MyAppliecation.getInstance().getPatchManager().addPatch("sdcard/andfix/" + file.getName());
//                    MyAppliecation.getInstance().getPatchManager().loadPatch();
//                    Thread.sleep(2000);//等待5s之后删除补丁文件
//                    boolean deleteFile = context.deleteFile(file.getName());
                            FileOutputStream fos = new FileOutputStream("sdcard/andfix/text.txt", true);
                            fos.write(("文件名：" + url.substring(url.lastIndexOf("/") + 1)).getBytes("UTF-8"));
                            fos.flush();
                            fos.close();
//                    file.renameTo(new File("ceshi.apatch"));
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
    }


    private void uploadFixData(final String url) {
        final File file = new File(context.getFilesDir(), "andfix");
        if (!file.exists() && !file.mkdirs()) {
            return;
        }
        final String target = file.getAbsolutePath() + "/" + url.substring(url.lastIndexOf("/") + 1);
        new HttpUtils().download(url, target, new RequestCallBack<File>() {
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
//                    KLog.e("sdcard/andfix/" + file.getName());
                    MyAppliecation.getInstance().getPatchManager().removeAllPatch();//将之前的全部删除即可
                    MyAppliecation.getInstance().getPatchManager().addPatch(target);
                    /**
                     * 保存下载地址
                     */
                    SharedPreferences sp = context.getSharedPreferences("andfix", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("target", target);
                    edit.commit();
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
    private void addLastApatch() {
        try {
            File file = new File(context.getFilesDir(), "andfix");
            if (!file.exists()){
                return;
            }
            SharedPreferences sp = context.getSharedPreferences("andfix", Context.MODE_PRIVATE);
            String lastApatchPath = file.getAbsolutePath() + "/" + sp.getString("hotfix", "hotfixID") + ".apatch";
//            patchManager.addPatch(lastApatchPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
