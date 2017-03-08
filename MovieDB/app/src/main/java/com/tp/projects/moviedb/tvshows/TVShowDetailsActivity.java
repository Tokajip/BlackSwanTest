package com.tp.projects.moviedb.tvshows;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.R;

public class TVShowDetailsActivity extends AppCompatActivity {

  private TVShowData show;
  private Context ctx;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tvshow_details);
    if (getIntent().getExtras() != null) {
      show = (TVShowData) getIntent().getExtras().get("show");
    }
    ctx = this;
    ImageView header = (ImageView) findViewById(R.id.show_detail_header);
    Picasso.with(ctx)
      .load(show.getBackdropPath())
      .error(R.mipmap.ic_launcher)
      .into(header);


  }
}
