package com.tp.projects.moviedb.movies;


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
import com.tp.projects.moviedb.MainActivity;
import com.tp.projects.moviedb.R;
import com.tp.projects.moviedb.TileAdapter;
import com.tp.projects.moviedb.util.JSONParser;
import com.tp.projects.moviedb.util.NetworkHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Call<JsonElement> call = NetworkHandler.getInstance().downloadMovieDataRetrofit();

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                parseMovieJSONData(response.body().getAsJsonObject());
                initializeTileLayout();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                String test = call.request().toString();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment, container, false);
        return mainView;
    }



    private FutureCallback<JsonObject> createMovieDBResponseHandler() {
        return new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e == null) {
                    if (result.get("status_code") != null) {
                        MainActivity.getInstance().setNetworkErrorFragmentVisible(result.get("status_code").getAsString(), result.get("status_message").getAsString());
                    } else {
                        parseMovieJSONData(result);
                        initializeTileLayout();
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

    private void initializeTileLayout() {
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
        recyclerView.setAdapter(new TileAdapter(ctx, searchedList));

    }
}
