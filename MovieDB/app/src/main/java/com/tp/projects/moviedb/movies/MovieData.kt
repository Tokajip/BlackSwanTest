package com.tp.projects.moviedb.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieData(

  /**
   * @return The posterPath
   */
        /**
   * @param posterPath The poster_path
   */
  @SerializedName("poster_path") @Expose var posterPath: String,
  /**
   * @return The adult
   */
        /**
   * @param adult The adult
   */
  @SerializedName("adult") @Expose val adult: Boolean,
  /**
   * @return The overview
   */
        /**
   * @param overview The overview
   */
  @SerializedName("overview") @Expose val overview: String,
  /**
   * @return The releaseDate
   */
        /**
   * @param releaseDate The release_date
   */
  @SerializedName("release_date") @Expose val releaseDate: String,
  /**
   * @return The genreIds
   */
        /**
   * @param genreIds The genre_ids
   */
  @SerializedName("genre_ids") @Expose val genreIds: List<Int> = listOf(),
  /**
   * @return The id
   */
        /**
   * @param id The id
   */
  @SerializedName("id") @Expose val id: Int,
  /**
   * @return The originalTitle
   */
        /**
   * @param originalTitle The original_title
   */
  @SerializedName("original_title") @Expose val originalTitle: String,
  /**
   * @return The originalLanguage
   */
        /**
   * @param originalLanguage The original_language
   */
  @SerializedName("original_language") @Expose val originalLanguage: String,
  /**
   * @return The title
   */
        /**
   * @param title The title
   */
  @SerializedName("title") @Expose val title: String,
  /**
   * @return The backdropPath
   */
        /**
   * @param backdropPath The backdrop_path
   */
  @SerializedName("backdrop_path") @Expose var backdropPath: String,
  /**
   * @return The popularity
   */
        /**
   * @param popularity The popularity
   */
  @SerializedName("popularity") @Expose val popularity: Double,
  /**
   * @return The voteCount
   */
        /**
   * @param voteCount The vote_count
   */
  @SerializedName("vote_count") @Expose val voteCount: Int,
  /**
   * @return The video
   */
        /**
   * @param video The video
   */
  @SerializedName("video") @Expose val video: Boolean,
  /**
   * @return The voteAverage
   */
        /**
   * @param voteAverage The vote_average
   */
  @SerializedName("vote_average") @Expose val voteAverage: Double) : Serializable
