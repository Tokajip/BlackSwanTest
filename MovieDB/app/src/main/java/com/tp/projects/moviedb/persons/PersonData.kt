package com.tp.projects.moviedb.persons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by Peti on 2016. 07. 22..
 */
data class PersonData  (
    /**
     * @return The profilePath
     */
    /**
     * @param profilePath The profile_path
     */
    @SerializedName("profile_path")
    @Expose
    var profilePath: String,
    /**
     * @return The adult
     */
    /**
     * @param adult The adult
     */
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
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
     * @return The name
     */
    /**
     * @param name The name
     */
    @SerializedName("name")
    @Expose
    val name: String,
    /**
     * @return The popularity
     */
    /**
     * @param popularity The popularity
     */
    @SerializedName("popularity")
    @Expose
    val popularity: Double
): Serializable
