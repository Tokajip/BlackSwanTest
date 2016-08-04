package com.tp.projects.moviedb.util;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.JsonElement;
import com.tp.projects.moviedb.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Peti on 2016. 08. 04..
 */
public abstract class GeneralRetrofitResponseHandler implements Callback<JsonElement> {

    final FragmentActivity mActivity;
    Call<JsonElement> mCall;
    Response<JsonElement> mResponse;

    public GeneralRetrofitResponseHandler(FragmentActivity mActivity) {
        this.mActivity = mActivity;
        mCall = null;
        mResponse = null;
    }

    @Override
    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
        mResponse = response;
        mCall = call;
        if (!response.isSuccessful() && mActivity != null) {
            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, NetworkErrorFragment.newInstance(String.valueOf(response.code()), response.message())).commit();
        } else {
            responseHandler(call,response);
        }
    }

    @Override
    public void onFailure(Call<JsonElement> call, Throwable t) {
        if(mActivity != null){
            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, new OfflineFragment()).commit();
        }
    }

    public abstract void responseHandler(Call<JsonElement> mCall, Response<JsonElement> mResponse);

}
