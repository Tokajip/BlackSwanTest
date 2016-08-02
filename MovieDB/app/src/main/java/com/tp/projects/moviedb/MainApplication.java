package com.tp.projects.moviedb;

import android.app.Application;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.tp.projects.moviedb.util.NetworkHandler;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        NetworkHandler.getInstance().initialize(this);
        NetworkHandler.getInstance().downloadConfig(this, new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null && result.get("status_code") == null)
                    NetworkHandler.getInstance().initializeMovieDB(result.get("images").getAsJsonObject().get("base_url").getAsString());
            }
        });
    }
}
