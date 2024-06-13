package com.hss01248.apkinstaller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ReflectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.activityresult.ActivityResultListener;
import com.hss01248.activityresult.StartActivityUtil;
import com.hss01248.activityresult.TheActivityListener;
import com.hss01248.openuri2.OpenUri2;


import java.io.File;

/**
 * @Despciption todo
 * @Author hss
 * @Date 13/07/2022 17:21
 * @Version 1.0
 */
public class ApkInstallUtil {


    public static void checkAndInstallApk(String filePath, InstallCallback callback){
        File file = new File(filePath);

        Activity activity =  ActivityUtils.getTopActivity();
        if(activity instanceof FragmentActivity){
            checkAndInstallApk((FragmentActivity)activity,file, callback);
        }else {
            callback.onError("4","top activity not instanceof FragmentActivity");
        }
    }
    /*
     *
     * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
     */
    public static void checkAndInstallApk(final FragmentActivity activity, final File file, InstallCallback callback) {
        LogUtils.d("prepare to install apk:  "+file.getAbsolutePath());
        if(!file.exists()){
            callback.onError("1","file not exist");
            return;
        }
        if(!file.isFile()){
            callback.onError("2","path is not file");
            return;
        }
        if(file.length() ==0){
            callback.onError("3","file size is 0B");
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = activity.getPackageManager().canRequestPackageInstalls();
            if (b) {
                installApk(activity, file,callback);
            } else {
                ToastUtils.showLong(activity.getResources().getString(R.string.install_please_open_install_permission));
                //  引导用户手动开启安装权限
                Uri packageURI = Uri.parse("package:" + AppUtils.getAppPackageName());//设置这个才能
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                //startActivityForResult(intent, 235);
                StartActivityUtil.startActivity(activity, null,intent,true, new TheActivityListener<Activity>() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                        installApk(activity, file,callback);
                    }

                    @Override
                    public void onActivityNotFound(Throwable e) {
                        e.printStackTrace();
                    }
                });

                /*PFragment pFragment = new PFragment();
                activity.getSupportFragmentManager().beginTransaction().add(pFragment, UUID.randomUUID().toString()).commitNowAllowingStateLoss();
                pFragment.askInstallPermission(activity, new Runnable() {
                    @Override
                    public void run() {
                        installApk(activity, file);
                    }
                });*/
            }
        } else {
            installApk(activity, file,callback);
        }
    }

    private static void installApk(FragmentActivity activity, File file, InstallCallback callback) {

        try {
            Uri uri = OpenUri2.fromFile(activity, file);
            LogUtils.w(uri.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivity(intent);
            callback.onInstall();
        }catch (Throwable throwable){
            throwable.printStackTrace();
           callback.onError(throwable.getClass().getSimpleName(),throwable.getMessage());
        }

    }
}
