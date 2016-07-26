package com.tp.projects.moviedb.persons;

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

/**
 * Created by Peti on 2016. 07. 22..
 */
public class PersonTilesAdapter extends RecyclerView.Adapter<PersonTilesAdapter.Viewholder> {

    Context ctx;
    List<PersonData> list;

    public PersonTilesAdapter(Context context, List<PersonData> personList) {
        ctx = context;
        list = new ArrayList<>();
        list.addAll(personList);
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        View tile;

        public Viewholder(View itemView) {
            super(itemView);
            tile = itemView;
        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tile = LayoutInflater.from(ctx).inflate(R.layout.tile_layout, parent, false);
        return new Viewholder(tile);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        ((TextView) holder.tile.findViewById(R.id.tile_title)).setText(list.get(position).getName());
        ((TextView) holder.tile.findViewById(R.id.tile_popularity)).setText(String.valueOf(list.get(position).getPopularity()));
        String movies = "";
        for (PersonData.KnownFor movie : list.get(position).getKnownFor()) {
            movies += movie.getTitle() + ", ";
        }
        ((TextView) holder.tile.findViewById(R.id.tile_description)).setText(movies);
        holder.tile.findViewById(R.id.more_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, PersonDetailsActivity.class);
                intent.putExtra("person", list.get(position));
                ctx.startActivity(intent);
            }
        });

        Picasso.with(ctx)
                .load(list.get(position).getProfilePath())
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) holder.tile.findViewById(R.id.tile_image));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
