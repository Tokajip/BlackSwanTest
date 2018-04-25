package com.tp.projects.moviedb.persons

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.tp.projects.moviedb.R

class PersonDetailsActivity : AppCompatActivity() {

    var person: PersonData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        if (intent.extras != null) {
            person = intent.extras!!.get("person") as PersonData
        }
        val header = findViewById<ImageView>(R.id.person_detail_header)
        Picasso.get().load(person?.profilePath).error(R.mipmap.ic_launcher).into(header)

    }
}
