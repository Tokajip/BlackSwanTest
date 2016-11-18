package com.tp.projects.moviedb.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompatBase;

import com.google.gson.JsonElement;
import com.tp.projects.moviedb.MainActivity;
import com.tp.projects.moviedb.R;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import rx.Observer;

import static com.tp.projects.moviedb.Const.IS_CONNECTED;

/**
 * Created by Peti on 2016. 08. 04..
 */
public abstract class GeneralRetrofitResponseHandler implements Observer<JsonElement>{

  final FragmentActivity mActivity;
  Call<JsonElement> mCall;
  Response<JsonElement> mResponse;

  public GeneralRetrofitResponseHandler(FragmentActivity mActivity) {
    this.mActivity = mActivity;
    mCall = null;
    mResponse = null;
  }

  @Override
  public void onNext(JsonElement jsonElement) {
    responseHandler(jsonElement);
  }

  @Override
  public void onError(Throwable e) {
    if (IS_CONNECTED) {
      FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.fragment, NetworkErrorFragment.newInstance("wrong", "Something")).commit();
    } else {
      if (MainActivity.getInstance() != null) {
        MainActivity.getInstance().setOfflineFragmentVisible();
      }
    }
  }

  @Override
  public void onCompleted() {

  }


  public abstract void responseHandler(JsonElement jsonElement);

}
