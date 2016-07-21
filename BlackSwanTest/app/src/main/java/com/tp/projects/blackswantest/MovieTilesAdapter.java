package com.tp.projects.blackswantest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tokaji Peter on 21/07/16.
 */
public class MovieTilesAdapter extends RecyclerView.Adapter<MovieTilesAdapter.Viewholder> {

    public static class Viewholder extends RecyclerView.ViewHolder {

        View tile;
        public Viewholder(View itemView) {
            super(itemView);
            tile = itemView;
        }
    }

    Context ctx;
    List<MovieTile> movieList;

    public MovieTilesAdapter(Context context, List<MovieTile> movieTiles) {
        ctx = context;
        movieList = new ArrayList<>();
        movieList.addAll(movieTiles);
    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tile = LayoutInflater.from(ctx).inflate(R.layout.movie_tile, parent, false);

        return new Viewholder(tile);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        ((TextView)holder.tile.findViewById(R.id.movie_title)).setText(movieList.get(position).getTitle());
        ((TextView)holder.tile.findViewById(R.id.movie_subtitle)).setText(movieList.get(position).getOriginalLanguage());


        Picasso.with(ctx)
                .load(movieList.get(position).getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) holder.tile.findViewById(R.id.movie_image));

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
