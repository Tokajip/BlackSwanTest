package com.tp.projects.blackswantest.util;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

/**
 * Created by Peti on 2016. 07. 21..
 */
public class DBResponseHandler implements FutureCallback<JsonObject> {

    Context ctx;

    public DBResponseHandler(Context context) {
        ctx = context;
    }

    @Override
    public void onCompleted(Exception e, JsonObject result) {
        if (e == null) {
            if (result.get("status_code") != null) {
                Toast.makeText(ctx, "No Internet", Toast.LENGTH_SHORT).show();
                e = new Exception();
            }
        }
    }
}
