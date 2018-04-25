package com.tp.projects.moviedb.persons

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
 * Created by Peti on 2016. 07. 22..
 */

internal class PersonTilesAdapter(private val ctx: Context, private val personList: List<PersonData>) :
        RecyclerView.Adapter<PersonTilesAdapter.ViewHolder>() {

    internal class ViewHolder(var tile: View) : RecyclerView.ViewHolder(tile)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.tile_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actual = personList[position]
        holder.tile.apply {
            findViewById<TextView>(R.id.tile_title).text = actual.name
            findViewById<TextView>(R.id.tile_popularity).text = "Popularity = ${actual.popularity}"
            findViewById<Button>(R.id.more_info).setOnClickListener {
                ctx.startActivity(Intent(ctx, PersonDetailsActivity::class.java).apply {
                    putExtra("person",actual)
                })
            }
            Picasso.get().load(actual.profilePath).placeholder(R.mipmap.ic_launcher)
                    .into(findViewById<ImageView>(R.id.tile_image))
        }
        //Todo: Parse knowfor attribute
        /*var movies = ""
        for (movie in this.personList[position].knownFor) {
            movies += movie.title!! + ", "
        }
        (holder.tile.findViewById(R.id.tile_description) as TextView).text = movies*/
    }

    override fun getItemCount(): Int {
        return this.personList.size
    }

}
