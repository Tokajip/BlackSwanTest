package com.tp.projects.moviedb.util;

import com.tp.projects.moviedb.MainActivity;
import com.tp.projects.moviedb.MainApplication;
import com.tp.projects.moviedb.movies.MovieFragment;
import com.tp.projects.moviedb.persons.PersonsFragment;
import com.tp.projects.moviedb.tvshows.TVShowFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MovieDBModule.class)
public interface MovieDBComponent {

  void inject(MainActivity activity);
  void inject(MainApplication application);
  void inject(NetworkHandler handler);
  void inject(MovieFragment movieFragment);
  void inject(TVShowFragment tvShowFragment);
  void inject(PersonsFragment personsFragment);

}
