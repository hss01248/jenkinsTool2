package com.hss01248.apkinstaller;

public interface InstallCallback {

   default void onInstall(){

    }

    void onError(String code,String msg);
}
