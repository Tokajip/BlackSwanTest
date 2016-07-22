package com.tp.projects.blackswantest;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tp.projects.blackswantest.util.JSONParser;
import com.tp.projects.blackswantest.util.MovieDBResponseHandler;
import com.tp.projects.blackswantest.util.NetworkHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private Context ctx;
    private List<MovieTile> list;
    private View mainView;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctx = getActivity();
        movieDataResponseHandler = cretateMovieDBResponseHandler();
        NetworkHandler.downloadMovieData(ctx, movieDataResponseHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_main,container,false);
        return mainView;
    }

    MovieDBResponseHandler movieDataResponseHandler;

    private MovieDBResponseHandler cretateMovieDBResponseHandler() {
        return new MovieDBResponseHandler(ctx) {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                super.onCompleted(e, result);
                if (e == null) {
                    parseMovieJSONData(result);
                    initializeTileLayout();
                }
            }
        };

    }


    private void parseMovieJSONData(JsonObject result) {
        list = new ArrayList<>();
        JsonArray jsonList = result.getAsJsonArray("results");
        for (JsonElement movieJSON : jsonList) {
            MovieTile movie = (MovieTile) JSONParser.returnParsedClass(movieJSON, MovieTile.class);
            movie.setImageURLs();
            list.add(movie);
        }
    }

    private void initializeTileLayout() {
        RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.movie_tiles_container);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            recyclerView.setAdapter(new MovieTilesAdapter(ctx, list));
        }
    }




}
