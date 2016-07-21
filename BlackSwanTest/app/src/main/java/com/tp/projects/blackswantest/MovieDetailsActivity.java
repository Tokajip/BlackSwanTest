package com.tp.projects.blackswantest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tp.projects.blackswantest.util.NetworkHandler;

public class MovieDetailsActivity extends AppCompatActivity {

    Context ctx;
    MovieTile movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if(getIntent().getExtras() != null) {
            movie = (MovieTile) getIntent().getExtras().get("movie");
        }
        ctx = this;
        ImageView header = (ImageView) findViewById(R.id.movie_detail_header);

        Picasso.with(ctx)
                .load(NetworkHandler.createHeaderImageURL(movie.getBackdropPath()))
                .error(R.mipmap.ic_launcher)
                .into(header);


    }

}
