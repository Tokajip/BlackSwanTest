package com.tp.projects.moviedb;

import android.app.Application;

import com.google.gson.JsonElement;
import com.tp.projects.moviedb.util.GeneralRetrofitResponseHandler;
import com.tp.projects.moviedb.util.MovieDBComponentInjector;
import com.tp.projects.moviedb.util.NetworkHandler;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class MainApplication extends Application {

  @Inject NetworkHandler networkHandler;

  @Override
  public void onCreate() {
    super.onCreate();
    MovieDBComponentInjector.instance().inject(this);

    networkHandler.downloadConfigData()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Action1<JsonElement>() {
        @Override
        public void call(JsonElement jsonElement) {
          networkHandler.initializeMovieDB(jsonElement.getAsJsonObject().get("images").getAsJsonObject().get("base_url").getAsString());
        }
      });
  }
}
