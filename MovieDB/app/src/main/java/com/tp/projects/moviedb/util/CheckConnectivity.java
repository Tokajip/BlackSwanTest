package com.tp.projects.moviedb.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tp.projects.moviedb.MainActivity;

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
            if (MainActivity.getInstance() != null) {
                MainActivity.getInstance().restart();
            }
        } else {
            if (MainActivity.getInstance() != null) {
                MainActivity.getInstance().setOfflineFragmentVisible();
            }
        }
    }
}
