package com.tp.projects.moviedb.movies

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tp.projects.moviedb.R
import com.tp.projects.moviedb.util.NetworkHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import timber.log.Timber

class MovieFragment : Fragment() {

    private val networkHandler: NetworkHandler by inject()

    private val disposeBag = CompositeDisposable()

    private lateinit var mainView: View

    private var searchedList = listOf<MovieData>()
    private var movieList = mutableListOf<MovieData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString("searchedList")?.let { word ->
            searchedList = movieList.filter { it.title.contains(word) }
        }

        networkHandler.downloadMovieDataRetrofit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    //Todo: Need to refactor URL creation
                    movieList.addAll(movies.results.map { it.apply { setImageURLs(it) } })
                    arguments?.getString("searchedList")?.let { word ->
                        searchedList = movieList.filter {
                            val lowCaseTitle = it.title.toLowerCase()
                            lowCaseTitle.contains(word)
                        }
                    }
                    initializeTileLayout(requireContext())
                }, { Timber.e(it) })
                .apply { disposeBag.add(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainView = inflater.inflate(R.layout.fragment, container, false)
        return mainView
    }

    private fun initializeTileLayout(ctx: Context) {
        mainView.findViewById<RecyclerView>(R.id.tiles_container).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(ctx)
            adapter = if (searchedList.isEmpty()) {
                MovieTilesAdapter(ctx, movieList)
            } else {
                MovieTilesAdapter(ctx, searchedList)
            }
        }
    }

    private fun setImageURLs(movie: MovieData) {
        movie.posterPath = networkHandler.createTileImageURL(movie.posterPath)
        movie.backdropPath = networkHandler.createHeaderImageURL(movie.backdropPath)
    }

}
