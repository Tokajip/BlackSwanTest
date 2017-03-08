package com.tp.projects.moviedb;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tp.projects.moviedb.movies.MovieFragment;
import com.tp.projects.moviedb.persons.PersonsFragment;
import com.tp.projects.moviedb.tvshows.TVShowFragment;
import com.tp.projects.moviedb.util.SyncDataWear;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener, MaterialSearchView.OnQueryTextListener, MaterialSearchView.SearchViewListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

  private static final String TAG = "MainActivity";
  private MaterialSearchView searchView;
  private MenuItem searchIcon;
  private Context ctx;
  private GoogleApiClient googleApiClient;

  private List<String> testList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ctx = this;
    setNavigationDrawer(toolbar);
    if (savedInstanceState == null) {
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.fragment, new MovieFragment());
      transaction.commit();
    }
    setSearchView();
    setWearDataSync();

  }

  private void setNavigationDrawer(Toolbar toolbar) {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void setWearDataSync() {
    googleApiClient = new GoogleApiClient.Builder(this).
      addConnectionCallbacks(this)
      .addOnConnectionFailedListener(this)
      .addApi(Wearable.API)
      .build();
    googleApiClient.connect();
  }

  private void setSearchView() {
    searchView = (MaterialSearchView) findViewById(R.id.search_view);
    searchView.setOnQueryTextListener(this);
    searchView.setOnSearchViewListener(this);
  }


  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
    testList.add("test");
  }


  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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

  @Override
  public boolean onQueryTextSubmit(String query) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    Fragment searchedFragment = new MovieFragment();
    Bundle bundle = new Bundle();
    bundle.putString("searchedList", query);
    searchedFragment.setArguments(bundle);
    transaction.replace(R.id.fragment, searchedFragment);
    searchIcon.setVisible(true);
    transaction.commit();
    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    return false;
  }

  @Override
  public void onSearchViewShown() {
    searchView.setVisibility(View.VISIBLE);
  }

  @Override
  public void onSearchViewClosed() {
    searchView.setVisibility(View.GONE);
  }

  @Override
  public void onConnected(@Nullable Bundle bundle) {
    Log.d(TAG, "onConnected: " + (bundle != null ? bundle : ""));
    testList = new ArrayList<>();
    testList.add("test1");
    testList.add("test2");
    testList.add("test3");
    new SyncDataWear(googleApiClient).execute(testList);
  }

  @Override
  public void onConnectionSuspended(int i) {
    Log.d(TAG, "onConnectionSuspended: " + i);
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Log.d(TAG, "onConnectionFailed: " + connectionResult);
  }
}
