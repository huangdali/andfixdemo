package com.hdl.andfixdemo.bean;

/**
 * 补丁的javabean对象
 * Created by HDL on 2016/9/8.
 */
public class PatchBean {


    private int versionCode;

    private String patchUrl;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }


    @Override
    public String toString() {
        return "PatchBean{" +
                "versionCode=" + versionCode +
                ", apkpatchsurl='" + patchUrl + '\'' +
                '}';
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }
}
