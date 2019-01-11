package com.lmq.tool;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangminqiang on 2018/9/21.
 */

public class PermisstionCheck {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean checkAndRequestPermission(Activity activity) {

        try {

            List<String> lackedPermission = new ArrayList<String>();
            if (!(activity.checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.INTERNET);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.CAMERA);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
            }


            if (!(activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.BLUETOOTH);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.BLUETOOTH_ADMIN);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
          /*  if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }*/

            // 权限都已经有了，那么直接调用SDK
            if (lackedPermission.size() == 0) {

                return true;
            } else {
                // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
                String[] requestPermissions = new String[lackedPermission.size()];
                lackedPermission.toArray(requestPermissions);
                activity.requestPermissions(requestPermissions, 1024);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean checkAndRequestPermission_Bluetooth(Activity activity) {

        try {

            List<String> lackedPermission = new ArrayList<String>();
            if (!(activity.checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.INTERNET);
            }



            if (!(activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.BLUETOOTH);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.BLUETOOTH_ADMIN);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
          /*  if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (!(activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }*/

            // 权限都已经有了，那么直接调用SDK
            if (lackedPermission.size() == 0) {

                return true;
            } else {
                // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
                String[] requestPermissions = new String[lackedPermission.size()];
                lackedPermission.toArray(requestPermissions);
                activity.requestPermissions(requestPermissions, 1024);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
