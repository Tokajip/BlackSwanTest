package com.tp.projects.moviedb.util;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.tp.projects.moviedb.R;

import retrofit2.Call;
import retrofit2.Retrofit;

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


    public String createGETUrl(String path) {
        return baseURL + path + "?api_key=" + apiKey;
    }

    public String createTileImageURL(String path) {
        return imageBaseUrl + tileImageWidth + path;
    }

    public String createHeaderImageURL(String path) {
        return imageBaseUrl + headerImageWidth + path;
    }

    public void initialize(Context context) {
        baseURL = context.getString(R.string.base_url);
        apiKey = context.getString(R.string.api_key);
    }



    public Call<JsonElement> downloadMovieDataRetrofit() {
        return service.getMovies(apiKey);


    }

    public void downloadTvShowData(Context ctx, FutureCallback<JsonObject> tvshowDataResponseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.getInstance().createGETUrl("tv/popular"))
                .asJsonObject()
                .setCallback(tvshowDataResponseHandler);
    }

    public void downloadPersonsData(Context ctx, FutureCallback<JsonObject> personDataResponseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.getInstance().createGETUrl("person/popular"))
                .asJsonObject()
                .setCallback(personDataResponseHandler);
    }

    public void downloadConfig(Context ctx, FutureCallback<JsonObject> responseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.getInstance().createGETUrl("configuration"))
                .asJsonObject()
                .setCallback(responseHandler);
    }

    public void initializeMovieDB(String url) {
        imageBaseUrl = url;
    }


}
