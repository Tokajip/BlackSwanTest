package com.tp.projects.blackswantest.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tp.projects.blackswantest.MainActivity;

/**
 * Created by Peti on 2016. 07. 23..
 */
public class CheckConnectivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            if (MainActivity.getInstace() != null) {
                MainActivity.getInstace().restart();
            }
        } else {
            if (MainActivity.getInstace() != null) {
                MainActivity.getInstace().setOfflineFragmentVisible();
            }
        }
    }
}
