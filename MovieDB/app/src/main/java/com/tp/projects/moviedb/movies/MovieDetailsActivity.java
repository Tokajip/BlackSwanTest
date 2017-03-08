package com.tp.projects.moviedb.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.R;

public class MovieDetailsActivity extends AppCompatActivity {

  Context ctx;
  MovieData movie;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_details);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    if (getIntent().getExtras() != null) {
      movie = (MovieData) getIntent().getExtras().get("movie");
    }
    toolbar.setTitle(movie.getTitle());
    toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
    ctx = this;
    ImageView header = (ImageView) findViewById(R.id.movie_detail_header);
    setUI(movie);
    Picasso.with(ctx)
      .load(movie.getBackdropPath())
      .error(R.mipmap.ic_launcher)
      .into(header);
    if (findViewById(R.id.value_adult) != null) {
      ((TextView) findViewById(R.id.value_adult)).setText(movie.getAdult() ? "Yes" : "No");
    }
  }

  public void setUI(MovieData movie) {
    ((TextView) findViewById(R.id.value_title)).setText(movie.getTitle());
    ((TextView) findViewById(R.id.value_date)).setText(movie.getReleaseDate());
    ((TextView) findViewById(R.id.value_orig_title)).setText(movie.getOriginalTitle());
    ((TextView) findViewById(R.id.value_overview)).setText(movie.getOverview());
    ((TextView) findViewById(R.id.value_popularity)).setText(movie.getPopularity().toString());
    ((TextView) findViewById(R.id.value_vote_avg)).setText(movie.getVoteAverage().toString());
  }
}
