package com.tp.projects.moviedb.util;

import android.content.Context;

import com.google.gson.JsonElement;
import com.tp.projects.moviedb.R;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Peti on 2016. 08. 02..
 */
public class NetworkHandler {
  private static NetworkHandler ourInstance = new NetworkHandler();

  public static NetworkHandler getInstance() {
    return ourInstance;
  }

  private NetworkHandler() {
  }

  private String baseURL;
  private String apiKey;
  private String imageBaseUrl;
  private String tileImageWidth = "w154";
  private String headerImageWidth = "w780";

  public Retrofit getRetrofit() {
    return retrofit;
  }

  public void setRetrofit(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  public MovieDBNetworkService getService() {
    return service;
  }

  public void setService(MovieDBNetworkService service) {
    this.service = service;
  }

  private Retrofit retrofit;
  private MovieDBNetworkService service;


  public String createTileImageURL(String path) {
    return imageBaseUrl + tileImageWidth + path;
  }

  public String createHeaderImageURL(String path) {
    return imageBaseUrl + headerImageWidth + path;
  }

  public void initialize(Context context) {
    baseURL = context.getString(R.string.base_url);
    apiKey = context.getString(R.string.api_key);
    retrofit = new Retrofit.Builder()
      .baseUrl(context.getString(R.string.base_url))
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    service = retrofit.create(MovieDBNetworkService.class);
  }


  public Observable<JsonElement> downloadMovieDataRetrofit() {
    return service.getMovies(apiKey);
  }

  public Observable<JsonElement> downloadTvShowDataRetrofit() {
    return service.getTvShows(apiKey);
  }

  public Observable<JsonElement> downloadPersonDataRetrofit() {
    return service.getPesons(apiKey);
  }

  public Observable<JsonElement> downloadConfigData() {
    return service.getConfig(apiKey);
  }


  public void initializeMovieDB(String url) {
    imageBaseUrl = url;
  }


}
