package com.tp.projects.moviedb.util;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Peti on 2016. 08. 02..
 */
public interface MovieDBNetworkService {

    @GET("movie/popular")
    Call<JsonElement> getMovies(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<JsonElement> getTvShows(@Query("api_key") String apiKey);

    @GET("person/popular")
    Call<JsonElement> getPesons(@Query("api_key") String apiKey);

    @GET("configuration")
    Call<JsonElement> getConfig(@Query("api_key") String apiKey);
}
