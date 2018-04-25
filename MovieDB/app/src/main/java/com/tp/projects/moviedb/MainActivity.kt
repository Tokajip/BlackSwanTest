package com.tp.projects.moviedb

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.tp.projects.moviedb.movies.MovieFragment
import com.tp.projects.moviedb.persons.PersonsFragment
import com.tp.projects.moviedb.tvshows.TVShowFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        MaterialSearchView.OnQueryTextListener, MaterialSearchView.SearchViewListener {

    private lateinit var searchIcon: MenuItem
    private lateinit var searchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setNavigationDrawer(toolbar)
        savedInstanceState?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment, MovieFragment())
                commit()
            }
        }
        setSearchView()
    }

    private fun setNavigationDrawer(toolbar: Toolbar) {
        findViewById<DrawerLayout>(R.id.drawer_layout).apply {
            addDrawerListener(ActionBarDrawerToggle(this@MainActivity, this, toolbar, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close).apply { syncState() })
        }
        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener(this)
    }

    private fun setSearchView() {
        searchView = findViewById<MaterialSearchView>(R.id.search_view).apply {
            setOnQueryTextListener(this@MainActivity)
            setOnSearchViewListener(this@MainActivity)
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        supportFragmentManager.beginTransaction().apply {
            when (item.itemId) {
                R.id.drawer_movie -> {
                    replace(R.id.fragment, MovieFragment())
                    searchIcon.isVisible = true
                }
            R.id.drawer_tvshow -> {
                replace(R.id.fragment, TVShowFragment())
                searchIcon.isVisible = false
            }
            R.id.drawer_person -> {
                replace(R.id.fragment, PersonsFragment())
                searchIcon.isVisible = false
            }
            }
            commit()
        }
        findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchIcon = menu.findItem(R.id.action_search)
        searchView.setMenuItem(searchIcon)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, MovieFragment().apply {
                arguments = bundleOf(Pair("searchedList", query))
            })
            searchIcon.isVisible = true
            commit()
        }
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

    override fun onSearchViewShown() {
        searchView.visibility = View.VISIBLE
    }

    override fun onSearchViewClosed() {
        searchView.visibility = View.GONE
    }

}
