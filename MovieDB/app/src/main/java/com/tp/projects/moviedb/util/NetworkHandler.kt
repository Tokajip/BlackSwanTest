package com.tp.projects.moviedb.util

import com.google.gson.JsonElement
import com.tp.projects.moviedb.movies.MovieData
import com.tp.projects.moviedb.persons.PersonData
import com.tp.projects.moviedb.tvshows.TVShowData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Peti on 2016. 08. 02..
 */
class NetworkHandler constructor(retrofit: Retrofit) {

  private val api: MovieDBNetworkService = retrofit.create(MovieDBNetworkService::class.java)
  private val apiKey = "0a08e38b874d0aa2d426ffc04357069d"
  private val tileImageWidth = "w154"
  private val headerImageWidth = "w780"
  private var imageBaseUrl: String? = null

  fun createTileImageURL(path: String) = imageBaseUrl + tileImageWidth + path

  fun createHeaderImageURL(path: String) = imageBaseUrl + headerImageWidth + path

  fun downloadMovieDataRetrofit() = api.getMovies(apiKey)

  fun downloadTvShowDataRetrofit() = api.getTvShows(apiKey)

  fun downloadPersonDataRetrofit() = api.getPersons(apiKey)

  fun downloadConfigData(): Single<JsonElement> = api.getConfig(apiKey)

  fun initializeMovieDB(url: String) {
    imageBaseUrl = url
  }

  internal interface MovieDBNetworkService {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String): Single<MoviesResponse>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String): Single<ShowsResponse>

    @GET("person/popular")
    fun getPersons(@Query("api_key") apiKey: String): Single<PersonResponse>

    @GET("configuration")
    fun getConfig(@Query("api_key") apiKey: String): Single<JsonElement>
  }
}

data class MoviesResponse(val results: List<MovieData>)
data class PersonResponse(val results: List<PersonData>)
data class ShowsResponse(val results: List<TVShowData>)
