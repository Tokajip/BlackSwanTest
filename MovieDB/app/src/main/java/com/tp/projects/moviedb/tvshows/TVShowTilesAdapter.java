package com.tp.projects.moviedb.tvshows;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.R;

import java.util.ArrayList;
import java.util.List;

class TVShowTilesAdapter extends RecyclerView.Adapter<TVShowTilesAdapter.ViewHolder> {

    private Context ctx;
    private List<TVShowData> showList;


    TVShowTilesAdapter(Context context, List<TVShowData> tvshowList) {
        ctx = context;
        showList = new ArrayList<>();
        showList.addAll(tvshowList);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View tvShow;
        ViewHolder(View itemView) {
            super(itemView);
            tvShow = itemView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tile = LayoutInflater.from(ctx).inflate(R.layout.tile_layout, parent, false);
        return new ViewHolder(tile);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ((TextView) holder.tvShow.findViewById(R.id.tile_title)).setText(showList.get(position).getName());
        ((TextView) holder.tvShow.findViewById(R.id.tile_popularity)).setText(String.valueOf(showList.get(position).getVoteAverage()));
        ((TextView) holder.tvShow.findViewById(R.id.tile_description)).setText(showList.get(position).getOverview());
        holder.tvShow.findViewById(R.id.more_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, TVShowDetailsActivity.class);
                intent.putExtra("show", showList.get(position));
                ctx.startActivity(intent);
            }
        });

        Picasso.with(ctx)
                .load(showList.get(position).getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) holder.tvShow.findViewById(R.id.tile_image));

    }

    @Override
    public int getItemCount() {
        return showList.size();
    }
}
