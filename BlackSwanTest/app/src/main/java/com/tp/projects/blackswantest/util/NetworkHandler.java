package com.tp.projects.blackswantest.util;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.tp.projects.blackswantest.R;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class NetworkHandler {

    private static String baseURL;
    private static String apiKey;
    private static String imageBaseUrl;
    private static String tileImageWidth = "w154";
    private static String headerImageWidth = "w300";


    public static String createGETUrl(String path) {
        return baseURL + path + "?api_key=" + apiKey;
    }

    public static String createTileImageURL(String path) {
        return imageBaseUrl + tileImageWidth + path;
    }

    public static String createHeaderImageURL(String path) {
        return imageBaseUrl + headerImageWidth + path;
    }

    public static void initialize(Context context) {
        baseURL = context.getString(R.string.base_url);
        apiKey = context.getString(R.string.api_key);
    }

    public static void downloadMovieData(Context ctx, FutureCallback<JsonObject> responseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.createGETUrl("movie/popular"))
                .asJsonObject()
                .setCallback(responseHandler);
    }

    public static void downloadTvShowData(Context ctx, DBResponseHandler tvshowDataResponseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.createGETUrl("tv/popular"))
                .asJsonObject()
                .setCallback(tvshowDataResponseHandler);
    }

    public static void downloadPersonsData(Context ctx, DBResponseHandler personDataResponseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.createGETUrl("person/popular"))
                .asJsonObject()
                .setCallback(personDataResponseHandler);
    }
    public static void downloadConfig(Context ctx, FutureCallback<JsonObject> responseHandler) {
        Ion.with(ctx)
                .load(NetworkHandler.createGETUrl("configuration"))
                .asJsonObject()
                .setCallback(responseHandler);
    }

    public static void initializeMovieDB(String url) {
        imageBaseUrl = url;
    }


}
