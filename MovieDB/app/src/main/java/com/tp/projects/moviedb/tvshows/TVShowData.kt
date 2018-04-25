package com.tp.projects.moviedb.tvshows

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tp.projects.moviedb.util.NetworkHandler

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by Peti on 2016. 07. 22..
 */
class TVShowData (

    /**
     * @return The posterPath
     */
        /**
     * @param posterPath The poster_path
     */
    @SerializedName("poster_path")
    @Expose
        var posterPath: String,
    /**
     * @return The popularity
     */
        /**
     * @param popularity The popularity
     */
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    /**
     * @return The id
     */
        /**
     * @param id The id
     */
    @SerializedName("id")
    @Expose
    val id: Int,
    /**
     * @return The backdropPath
     */
        /**
     * @param backdropPath The backdrop_path
     */
    @SerializedName("backdrop_path")
    @Expose
        var backdropPath: String,
    /**
     * @return The voteAverage
     */
        /**
     * @param voteAverage The vote_average
     */
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    /**
     * @return The overview
     */
        /**
     * @param overview The overview
     */
    @SerializedName("overview")
    @Expose
    val overview: String,
    /**
     * @return The firstAirDate
     */
        /**
     * @param firstAirDate The first_air_date
     */
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,
    /**
     * @return The originCountry
     */
        /**
     * @param originCountry The origin_country
     */
    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String> = mutableListOf(),
    /**
     * @return The genreIds
     */
        /**
     * @param genreIds The genre_ids
     */
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int> = mutableListOf(),
        /**
     * @return The originalLanguage
     */
    /**
     * @param originalLanguage The original_language
     */
        @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    /**
     * @return The voteCount
     */
    /**
     * @param voteCount The vote_count
     */
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,
    /**
     * @return The name
     */
    /**
     * @param name The name
     */
    @SerializedName("name")
    @Expose
    val name: String,
    /**
     * @return The originalName
     */
    /**
     * @param originalName The original_name
     */
    @SerializedName("original_name")
    @Expose
    val originalName: String
): Serializable 
