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
public class MovieFragment extends Fragment {

  @Inject
  NetworkHandler networkHandler;
  private Context ctx;
  private static List<MovieData> movieList;
  private List<MovieData> searchedList;
  private View mainView;

  public MovieFragment() {
    if (getArguments() != null) {
    String query = getArguments().getString("searchedList");
      if(query != null) {
        searchedList = new ArrayList<>();
        for (MovieData movie : movieList) {
          if (movie.getTitle().contains(query)) {
            searchedList.add(movie);
          }
        }
      }
    }
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MovieDBComponentInjector.instance().inject(this);
    ctx = getActivity();
    networkHandler.downloadMovieDataRetrofit()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new GeneralRetrofitResponseHandler(getActivity()) {
        @Override
        public void onNext(JsonElement jsonElement) {
          parseMovieJSONData(jsonElement.getAsJsonObject());
          initializeTileLayout();
        }

        @Override
        public void onCompleted() {
        }
      });

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mainView = inflater.inflate(R.layout.fragment, container, false);
    return mainView;
  }

  private void parseMovieJSONData(JsonObject result) {
    movieList = new ArrayList<>();
    JsonArray jsonList = result.getAsJsonArray("results");
    for (JsonElement showJSON : jsonList) {
      MovieData movie = (MovieData) JSONParser.returnParsedClass(showJSON, MovieData.class);
      setImageURLs(movie);
      movieList.add(movie);
    }
  }

  private void initializeTileLayout() {
    RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.tiles_container);
    if (recyclerView != null) {
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
      if (searchedList != null) {
        recyclerView.setAdapter(new MovieTilesAdapter(ctx, searchedList));
      } else {
        recyclerView.setAdapter(new MovieTilesAdapter(ctx, movieList));
      }
    }
  }

  public void setImageURLs(MovieData movie) {
    movie.setPosterPath(networkHandler.createTileImageURL(movie.getPosterPath()));
    movie.setBackdropPath(networkHandler.createHeaderImageURL(movie.getBackdropPath()));
  }
}
