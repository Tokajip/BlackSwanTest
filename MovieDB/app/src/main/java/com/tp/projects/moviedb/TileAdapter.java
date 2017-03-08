package com.tp.projects.moviedb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.movies.MovieData;
import com.tp.projects.moviedb.movies.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peti on 2016. 07. 26..
 */
public class TileAdapter extends RecyclerView.Adapter<TileAdapter.ViewHolder> {

  private Context ctx;
  private List<MovieData> list;

  public TileAdapter(Context ctx, List<MovieData> list) {
    this.ctx = ctx;
    this.list = new ArrayList<>();
    this.list.addAll(list);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View mainView = View.inflate(ctx, R.layout.tile_layout_new, parent);
    return new ViewHolder(mainView);
  }


  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    ((TextView) holder.view.findViewById(R.id.tile_title)).setText(list.get(position).getTitle());
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

  class ViewHolder extends RecyclerView.ViewHolder {
    View view;

    ViewHolder(View itemView) {
      super(itemView);
      view = itemView;
    }
  }
}
