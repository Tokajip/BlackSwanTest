package com.tp.projects.moviedb.util;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.JsonElement;
import com.tp.projects.moviedb.R;

import rx.Observer;

import static com.tp.projects.moviedb.Const.IS_CONNECTED;

public abstract class GeneralRetrofitResponseHandler implements Observer<JsonElement> {

  private final FragmentActivity mActivity;

  public GeneralRetrofitResponseHandler(FragmentActivity mActivity) {
    this.mActivity = mActivity;
  }

  @Override
  public void onError(Throwable e) {
    if (IS_CONNECTED) {
      FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.fragment, NetworkErrorFragment.newInstance("wrong", "Something")).commit();
    } else {
      FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.fragment, new OfflineFragment()).commit();
    }
  }

}
