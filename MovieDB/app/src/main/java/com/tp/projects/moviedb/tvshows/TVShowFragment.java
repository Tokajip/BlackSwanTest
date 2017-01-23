package com.tp.projects.moviedb.tvshows;


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
import com.tp.projects.moviedb.R;
import com.tp.projects.moviedb.util.GeneralRetrofitResponseHandler;
import com.tp.projects.moviedb.util.JSONParser;
import com.tp.projects.moviedb.util.MovieDBComponentInjector;
import com.tp.projects.moviedb.util.NetworkHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    @Inject NetworkHandler networkHandler;

    private Context ctx;
    private List<TVShowData> tvShowList;
    private View mainView;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieDBComponentInjector.instance().inject(this);

        ctx = getActivity();
        networkHandler.downloadTvShowDataRetrofit()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(cretateTvShowDBResponseHandler());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment, container, false);
        return mainView;
    }


    private GeneralRetrofitResponseHandler cretateTvShowDBResponseHandler() {
        return new GeneralRetrofitResponseHandler(getActivity()) {
            @Override
            public void responseHandler(JsonElement jsonElement) {
                parseTVShowJSONData(jsonElement.getAsJsonObject());
                initializeTileLayout();
            }
        };
    }


    private void parseTVShowJSONData(JsonObject result) {
        tvShowList = new ArrayList<>();
        JsonArray jsonList = result.getAsJsonArray("results");
        for (JsonElement movieJSON : jsonList) {
            TVShowData show = (TVShowData) JSONParser.returnParsedClass(movieJSON, TVShowData.class);
            setImageURLs(show);
            tvShowList.add(show);
        }
    }

    private void initializeTileLayout() {
        RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.tiles_container);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            recyclerView.setAdapter(new TVShowTilesAdapter(ctx, tvShowList));
        }
    }

    public void setImageURLs(TVShowData show) {
        show.setPosterPath(networkHandler.createTileImageURL(show.getPosterPath()));
        show.setBackdropPath(networkHandler.createHeaderImageURL(show.getBackdropPath()));
    }
}
