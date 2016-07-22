package com.tp.projects.blackswantest.tvshows;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tp.projects.blackswantest.R;
import com.tp.projects.blackswantest.movies.MovieTilesAdapter;
import com.tp.projects.blackswantest.util.JSONParser;
import com.tp.projects.blackswantest.util.DBResponseHandler;
import com.tp.projects.blackswantest.util.NetworkHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    private Context ctx;
    private List<TVShowData> tvshowList;
    private View mainView;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctx = getActivity();
        tvshowDataResponseHandler = cretateTvShowDBResponseHandler();
        NetworkHandler.downloadTvShowData(ctx, tvshowDataResponseHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_tvshow,container,false);
        return mainView;
    }

    DBResponseHandler tvshowDataResponseHandler;

    private DBResponseHandler cretateTvShowDBResponseHandler() {
        return new DBResponseHandler(ctx) {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                super.onCompleted(e, result);
                if (e == null) {
                    parseTVShowJSONData(result);
                    initializeTileLayout();
                }
            }
        };

    }


    private void parseTVShowJSONData(JsonObject result) {
        tvshowList = new ArrayList<>();
        JsonArray jsonList = result.getAsJsonArray("results");
        for (JsonElement movieJSON : jsonList) {
            TVShowData show = (TVShowData) JSONParser.returnParsedClass(movieJSON, TVShowData.class);
            show.setImageURLs();
            tvshowList.add(show);
        }
    }

    private void initializeTileLayout() {
        RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.shows_container);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            recyclerView.setAdapter(new TVShowTilesAdapter(ctx, tvshowList));
        }
    }




}
