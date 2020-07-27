package com.example.permission;

import android.app.Application;
import android.content.pm.PackageInfo;

public class AppData extends Application {

    PackageInfo info;

    public PackageInfo getPackageInfo(){
        return info;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        info = packageInfo;
    }

}
