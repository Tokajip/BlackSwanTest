package com.tp.projects.moviedb.tvshows;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.R;
import com.tp.projects.moviedb.databinding.ActivityTvshowDetailsBinding;

public class TVShowDetailsActivity extends AppCompatActivity {

    private TVShowData show;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTvshowDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tvshow_details);


        if (getIntent().getExtras() != null) {
            show = (TVShowData) getIntent().getExtras().get("show");
        }
        ctx = this;
        ImageView header = (ImageView) findViewById(R.id.show_detail_header);
        binding.setShow(show);

        Picasso.with(ctx)
                .load(show.getBackdropPath())
                .error(R.mipmap.ic_launcher)
                .into(header);


    }
}
