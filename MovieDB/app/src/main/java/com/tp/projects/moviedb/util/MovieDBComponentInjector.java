package com.tp.projects.moviedb.util;

import com.tp.projects.moviedb.MainActivity;
import com.tp.projects.moviedb.MainApplication;

import javax.inject.Singleton;

import dagger.Component;


public class MovieDBComponentInjector {
  public static MovieDBComponent instance() {
    return DaggerMovieDBComponent.builder()
      .movieDBModule(new MovieDBModule())
      .build();
  }
}
