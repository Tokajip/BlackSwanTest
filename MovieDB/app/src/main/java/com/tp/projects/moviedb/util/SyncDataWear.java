package com.tp.projects.moviedb.util;

import android.os.AsyncTask;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peti on 2016. 08. 21..
 */
public class SyncDataWear extends AsyncTask<List<String>, Void, Void> {

    private GoogleApiClient mGoogleApiClient;

    public SyncDataWear(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }

    @Override
    protected Void doInBackground(List<String>... lists) {
        PutDataMapRequest dataMapRequest = PutDataMapRequest.create("/movieDB/testStrings");
        for (List<String> list :
                lists) {
            dataMapRequest.getDataMap().putStringArrayList("1", (ArrayList<String>) list);
        }

        PutDataRequest request = dataMapRequest.asPutDataRequest();
        DataApi.DataItemResult dataItemResult = Wearable.DataApi.putDataItem(mGoogleApiClient,request).await();
        return null;
    }
}
