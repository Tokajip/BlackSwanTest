package com.tp.projects.moviedb.tvshows

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

internal class TVShowTilesAdapter(private val ctx: Context, private val tvshowList: List<TVShowData>) :
        RecyclerView.Adapter<TVShowTilesAdapter.ViewHolder>() {

    internal inner class ViewHolder(var tvShow: View) : RecyclerView.ViewHolder(tvShow)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.tile_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actual = tvshowList[position]
        holder.tvShow.apply {
            findViewById<TextView>(R.id.tile_title).text = actual.name
            findViewById<TextView>(R.id.tile_popularity).text = actual.voteAverage.toString()
            findViewById<TextView>(R.id.tile_description).text = actual.overview
            findViewById<Button>(R.id.more_info).setOnClickListener {
                ctx.startActivity(Intent(ctx, TVShowDetailsActivity::class.java).apply {
                    putExtra("show", actual)
                })
            }
            Picasso.get().load(actual.posterPath).placeholder(R.mipmap.ic_launcher)
                    .into(findViewById<ImageView>(R.id.tile_image))
        }


    }

    override fun getItemCount(): Int {
        return this.tvshowList.size
    }
}
