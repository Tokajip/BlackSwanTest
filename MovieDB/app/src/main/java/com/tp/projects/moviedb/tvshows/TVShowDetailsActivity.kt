package com.tp.projects.moviedb.tvshows

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.tp.projects.moviedb.R

class TVShowDetailsActivity : AppCompatActivity() {

    private var show: TVShowData? = null
    private var ctx: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_details)
        if (intent.extras != null) {
            show = intent.extras!!.get("show") as TVShowData
        }
        ctx = this
        val header = findViewById<ImageView>(R.id.show_detail_header)
        Picasso.get().load(show?.backdropPath).error(R.mipmap.ic_launcher).into(header)


    }
}
