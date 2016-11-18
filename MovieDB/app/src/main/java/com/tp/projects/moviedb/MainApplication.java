package com.tp.projects.moviedb;

import android.app.Application;

import com.google.gson.JsonElement;
import com.tp.projects.moviedb.util.GeneralRetrofitResponseHandler;
import com.tp.projects.moviedb.util.NetworkHandler;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class MainApplication extends Application {


  @Override
  public void onCreate() {
    super.onCreate();

    NetworkHandler.getInstance().initialize(this);
    NetworkHandler.getInstance().downloadConfigData()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new GeneralRetrofitResponseHandler(MainActivity.getInstance()) {
        @Override
        public void responseHandler(JsonElement jsonElement) {
          NetworkHandler.getInstance().initializeMovieDB(jsonElement.getAsJsonObject().get("images").getAsJsonObject().get("base_url").getAsString());
        }
      });
  }
}
