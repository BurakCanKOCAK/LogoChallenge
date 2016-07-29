package com.bkocak.logochallenge.utils;

import android.app.Application;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bkocak on 21/07/2016.
 */
public class PrintHashKey extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        printHashKeySSL();
    }

    public void printHashKeySSL() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.bkocak.logochallenge",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash : ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
