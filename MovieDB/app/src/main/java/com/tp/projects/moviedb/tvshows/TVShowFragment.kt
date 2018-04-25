package com.tp.projects.moviedb.tvshows

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


/**
 * A simple [Fragment] subclass.
 */

class TVShowFragment : Fragment() {

    private val networkHandler: NetworkHandler by inject()

    private val disposeBag = CompositeDisposable()

    private lateinit var mainView: View

    private var tvShowList = mutableListOf<TVShowData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkHandler.downloadTvShowDataRetrofit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tvShowList.addAll(it.results.map { it.apply { setImageURLs(it) } })
                    initializeTileLayout()
                }, { Timber.d(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment, container, false)
        return mainView
    }


    private fun initializeTileLayout() {
        (mainView.findViewById(R.id.tiles_container) as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TVShowTilesAdapter(requireContext(), tvShowList)
        }
    }

    private fun setImageURLs(show: TVShowData) {
        show.posterPath = networkHandler.createTileImageURL(show.posterPath)
        show.backdropPath = networkHandler.createHeaderImageURL(show.backdropPath)
    }
}

