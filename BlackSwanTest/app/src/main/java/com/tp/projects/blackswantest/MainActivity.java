package com.tp.projects.blackswantest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tp.projects.blackswantest.movies.MovieFragment;
import com.tp.projects.blackswantest.persons.PersonsFragment;
import com.tp.projects.blackswantest.tvshows.TVShowFragment;
import com.tp.projects.blackswantest.util.NetworkHandler;
import com.tp.projects.blackswantest.util.OfllineFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialSearchView searchView;
    private MenuItem searchIcon;
    static Context ctx;


    private static MainActivity mainActivityRunningInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;
        mainActivityRunningInstance = this;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, new MovieFragment());
            transaction.commit();
        }

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MovieFragment.setSearchedLayout(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                searchView.setVisibility(View.GONE);

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction = getSupportFragmentManager().beginTransaction();


        if (id == R.id.drawer_movie) {
            transaction.replace(R.id.fragment, new MovieFragment());
            searchIcon.setVisible(true);
        } else if (id == R.id.drawer_tvshow) {
            transaction.replace(R.id.fragment, new TVShowFragment());
            searchIcon.setVisible(false);
        } else if (id == R.id.drawer_person) {
            transaction.replace(R.id.fragment, new PersonsFragment());
            searchIcon.setVisible(false);
        }
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        searchIcon = menu.findItem(R.id.action_search);
        searchView.setMenuItem(searchIcon);

        return true;
    }

    public void setOfflineFragmentVisible() {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment,new OfllineFragment()).commit();
            }
        });

    }

    public static MainActivity  getInstace(){
        return mainActivityRunningInstance;
    }

    public void restart() {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment,new MovieFragment()).commit();
            }
        });
    }
}
