package com.tp.projects.blackswantest.movies;


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
import com.koushikdutta.async.future.FutureCallback;
import com.tp.projects.blackswantest.MainActivity;
import com.tp.projects.blackswantest.R;
import com.tp.projects.blackswantest.util.JSONParser;
import com.tp.projects.blackswantest.util.NetworkHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private static Context ctx;
    private static List<MovieData> movieList;
    private static List<MovieData> searchedList;
    private View mainView;
    private static RecyclerView recyclerView;

    public MovieFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctx = getActivity();
        NetworkHandler.downloadMovieData(ctx, cretateMovieDBResponseHandler());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment, container, false);
        return mainView;
    }


    private FutureCallback<JsonObject> cretateMovieDBResponseHandler() {
        return new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (result.get("status_code") != null) {
                        MainActivity.getInstace().setNetworkErrorFragmentVisible(result.get("status_code").getAsString(), result.get("status_message").getAsString());
                    } else {
                        parseMovieJSONData(result);
                        initializeTileLayout(e);
                    }

                }
            }
        };

    }


    private void parseMovieJSONData(JsonObject result) {
        movieList = new ArrayList<>();
        JsonArray jsonList = result.getAsJsonArray("results");
        for (JsonElement showJSON : jsonList) {
            MovieData movie = (MovieData) JSONParser.returnParsedClass(showJSON, MovieData.class);
            movie.setImageURLs();
            movieList.add(movie);
        }
    }

    private void initializeTileLayout(Exception e) {
        recyclerView = (RecyclerView) mainView.findViewById(R.id.tiles_container);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            recyclerView.setAdapter(new MovieTilesAdapter(ctx, movieList));
        }
    }


    public static void setSearchedLayout(String query) {
        searchedList = new ArrayList<MovieData>();
        for (MovieData movie : movieList) {
            if (movie.getTitle().contains(query)) {
                searchedList.add(movie);
            }
        }
        recyclerView.setAdapter(new MovieTilesAdapter(ctx, searchedList));

    }
}
