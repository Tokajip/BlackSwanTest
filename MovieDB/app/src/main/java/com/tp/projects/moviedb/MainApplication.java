package com.tp.projects.moviedb;

import android.app.Application;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.tp.projects.moviedb.util.GeneralRetrofitResponseHandler;
import com.tp.projects.moviedb.util.MovieDBNetworkService;
import com.tp.projects.moviedb.util.NetworkHandler;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        NetworkHandler.getInstance().initialize(this);
        NetworkHandler.getInstance().downloadConfigData(new GeneralRetrofitResponseHandler(MainActivity.getInstance()) {
            @Override
            public void responseHandler(Call<JsonElement> mCall, Response<JsonElement> mResponse) {
                NetworkHandler.getInstance().initializeMovieDB(mResponse.body().getAsJsonObject().get("images").getAsJsonObject().get("base_url").getAsString());
            }
        });
    }
}
