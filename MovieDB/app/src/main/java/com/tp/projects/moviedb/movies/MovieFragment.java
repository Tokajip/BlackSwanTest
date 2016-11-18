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
import com.tp.projects.moviedb.R;
import com.tp.projects.moviedb.TileAdapter;
import com.tp.projects.moviedb.util.GeneralRetrofitResponseHandler;
import com.tp.projects.moviedb.util.JSONParser;
import com.tp.projects.moviedb.util.NetworkHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    NetworkHandler.getInstance().downloadMovieDataRetrofit()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(createMovieDBResponseHandler());

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mainView = inflater.inflate(R.layout.fragment, container, false);
    return mainView;
  }


  private GeneralRetrofitResponseHandler createMovieDBResponseHandler() {
    return new GeneralRetrofitResponseHandler(getActivity()) {
      @Override
      public void responseHandler(JsonElement jsonElement) {
        parseMovieJSONData(jsonElement.getAsJsonObject());
        initializeTileLayout();
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
