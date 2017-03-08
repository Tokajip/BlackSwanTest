package com.tp.projects.moviedb.persons;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tp.projects.moviedb.R;

public class PersonDetailsActivity extends AppCompatActivity {

  Context ctx;
  PersonData person;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_person_details);
    if (getIntent().getExtras() != null) {
      person = (PersonData) getIntent().getExtras().get("person");
    }
    ctx = this;
    ImageView header = (ImageView) findViewById(R.id.person_detail_header);
    Picasso.with(ctx)
      .load(person.getProfilePath())
      .error(R.mipmap.ic_launcher)
      .into(header);

  }
}
