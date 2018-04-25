package com.tp.projects.moviedb.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tp.projects.moviedb.R

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val header = findViewById<ImageView>(R.id.movie_detail_header)
        setSupportActionBar(toolbar)

        intent.extras?.get("movie")?.let {
            toolbar.title = (it as MovieData).title
            setUI(it)
            Picasso.get().load(it.backdropPath).error(R.mipmap.ic_launcher).into(header)
            findViewById<TextView>(R.id.value_adult).text = if (it.adult) getString(android.R.string.yes) else getString(android.R.string.no)
        }
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_action_navigation_arrow_back)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    fun setUI(movie: MovieData) {
        movie.apply {
            findViewById<TextView>(R.id.value_title).text = title
            findViewById<TextView>(R.id.value_date).text = releaseDate
            findViewById<TextView>(R.id.value_orig_title).text = originalTitle
            findViewById<TextView>(R.id.value_overview).text = overview
            findViewById<TextView>(R.id.value_popularity).text = popularity.toString()
            findViewById<TextView>(R.id.value_vote_avg).text = voteAverage.toString()

        }
    }
}
