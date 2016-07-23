package com.tp.projects.blackswantest.persons;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tp.projects.blackswantest.R;
import com.tp.projects.blackswantest.databinding.ActivityPersonDetailsBinding;

public class PersonDetailsActivity extends AppCompatActivity {

    Context ctx;
    PersonData person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersonDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_person_details);


        if (getIntent().getExtras() != null) {
            person = (PersonData) getIntent().getExtras().get("person");
        }
        ctx = this;
        ImageView header = (ImageView) findViewById(R.id.person_detail_header);
        binding.setPerson(person);

        Picasso.with(ctx)
                .load(person.getProfilePath())
                .error(R.mipmap.ic_launcher)
                .into(header);

    }
}
