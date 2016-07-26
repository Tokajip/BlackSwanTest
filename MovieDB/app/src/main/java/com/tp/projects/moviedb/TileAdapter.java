package com.tp.projects.moviedb;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.databinding.TileLayoutNewBinding;
import com.tp.projects.moviedb.movies.MovieData;
import com.tp.projects.moviedb.movies.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peti on 2016. 07. 26..
 */
public class TileAdapter extends RecyclerView.Adapter<TileAdapter.Viewholder> {

    private Context ctx;
    private List<MovieData> list;

    public TileAdapter(Context ctx, List<MovieData> list) {
        this.ctx = ctx;
        this.list = new ArrayList<>();
        this.list.addAll(list);
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        TileLayoutNewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.tile_layout_new, parent, false);
        View mainView = binding.getRoot();
        return new Viewholder(mainView,binding);
    }



    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.binding.setItem(list.get(position));
        Picasso.with(ctx)
                .load(list.get(position).getBackdropPath())
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) holder.view.findViewById(R.id.poster_image));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, MovieDetailsActivity.class);
                intent.putExtra("movie", list.get(position));
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        View view;
        TileLayoutNewBinding binding;

        public Viewholder(View itemView, TileLayoutNewBinding binding) {
            super(itemView);
            view = itemView;
            this.binding = binding;
        }
    }
}
