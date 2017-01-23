package com.tp.projects.moviedb.util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieDBModule {

  @Provides
  @Singleton
  public Retrofit provideRetrofit() {
    return new Retrofit.Builder()
      .baseUrl("http://api.themoviedb.org/3/")
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build();
  }


}
