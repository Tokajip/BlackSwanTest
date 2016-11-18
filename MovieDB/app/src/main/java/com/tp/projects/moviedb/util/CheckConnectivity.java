package com.tp.projects.moviedb.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tp.projects.moviedb.MainActivity;

import static com.tp.projects.moviedb.Const.IS_CONNECTED;

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
      IS_CONNECTED = true;
      if (MainActivity.getInstance() != null) {
        MainActivity.getInstance().restart();
      }
    } else {
      IS_CONNECTED = false;
      if (MainActivity.getInstance() != null) {
        MainActivity.getInstance().setOfflineFragmentVisible();
      }
    }
  }
}
