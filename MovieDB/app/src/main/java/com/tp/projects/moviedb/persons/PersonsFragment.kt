package com.tp.projects.moviedb.persons

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

/**
 * A simple [Fragment] subclass.
 */

class PersonsFragment : Fragment() {

    private val networkHandler: NetworkHandler by inject()

    private val disposeBag = CompositeDisposable()


    private var ctx: Context? = null
    private var personList = mutableListOf<PersonData>()
    private lateinit var mainView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkHandler.downloadPersonDataRetrofit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    personList.addAll(it.results.apply { map { it.profilePath = networkHandler.createTileImageURL(it.profilePath) } })
                    initializeTileLayout()
                }, { Timber.e(it) })
                .apply { disposeBag.add(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment, container, false)
        return mainView
    }

    private fun initializeTileLayout() {
        (mainView.findViewById(R.id.tiles_container) as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(ctx)
            adapter = PersonTilesAdapter(requireContext(), personList)
        }
    }


}
