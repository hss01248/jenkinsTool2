package com.hss01248.jenkinsdownloader;

import androidx.multidex.MultiDexApplication;

import com.hss01248.jenkins.JenkinsTool;
import com.liulishuo.filedownloader.FileDownloader;


/**
 * @Despciption todo
 * @Author hss
 * @Date 02/12/2021 16:32
 * @Version 1.0
 */
public class BaseApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        JenkinsTool.init("http://xxx/api/json", "yyy",  "oooo");

        FileDownloader.setupOnApplicationOnCreate(this);

    }
}
