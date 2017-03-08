package com.tp.projects.moviedb.util;

import com.google.gson.JsonElement;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Peti on 2016. 08. 02..
 */
@Singleton
public class NetworkHandler {

  @Inject
  NetworkHandler(Retrofit retrofit) {
    service = retrofit.create(MovieDBNetworkService.class);
    apiKey = "0a08e38b874d0aa2d426ffc04357069d";
    MovieDBComponentInjector.instance().inject(this);
  }

  private String apiKey;
  private static String imageBaseUrl;
  private String tileImageWidth = "w154";
  private String headerImageWidth = "w780";

  private MovieDBNetworkService service;

  public String createTileImageURL(String path) {
    return imageBaseUrl + tileImageWidth + path;
  }

  public String createHeaderImageURL(String path) {
    return imageBaseUrl + headerImageWidth + path;
  }


  public Observable<JsonElement> downloadMovieDataRetrofit() {
    return service.getMovies(apiKey);
  }

  public Observable<JsonElement> downloadTvShowDataRetrofit() {
    return service.getTvShows(apiKey);
  }

  public Observable<JsonElement> downloadPersonDataRetrofit() {
    return service.getPersons(apiKey);
  }

  public Observable<JsonElement> downloadConfigData() {
    return service.getConfig(apiKey);
  }


  public void initializeMovieDB(String url) {
    imageBaseUrl = url;
  }


  interface MovieDBNetworkService {

    @GET("movie/popular")
    Observable<JsonElement> getMovies(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Observable<JsonElement> getTvShows(@Query("api_key") String apiKey);

    @GET("person/popular")
    Observable<JsonElement> getPersons(@Query("api_key") String apiKey);

    @GET("configuration")
    Observable<JsonElement> getConfig(@Query("api_key") String apiKey);
  }

}
