package com.tp.projects.moviedb.movies

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tp.projects.moviedb.R

/**
 * Created by Tokaji Peter on 21/07/16.
 */
internal class MovieTilesAdapter(private val ctx: Context, private val movieList: List<MovieData>) :
        RecyclerView.Adapter<MovieTilesAdapter.ViewHolder>() {

    internal class ViewHolder(var tile: View) : RecyclerView.ViewHolder(tile)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.tile_layout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tile.apply {
            val actualMovie = movieList[position]
            findViewById<TextView>(R.id.tile_title).text = actualMovie.title
            findViewById<TextView>(R.id.tile_popularity).text = "Rating: ${actualMovie.voteAverage}"
            findViewById<TextView>(R.id.tile_description).text = actualMovie.overview
            findViewById<Button>(R.id.more_info).setOnClickListener {
                ctx.startActivity(Intent(ctx, MovieDetailsActivity::class.java).apply {
                    putExtra("movie", actualMovie)
                })
            }
            Picasso.get()
                    .load(movieList[position].posterPath)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(findViewById<ImageView>(R.id.tile_image))
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
