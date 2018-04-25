package com.tp.projects.moviedb

import android.app.Application
import com.tp.projects.moviedb.di.movieDBModule
import com.tp.projects.moviedb.util.NetworkHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Peti on 2016. 07. 21..
 */
class MainApplication : Application() {

    private val networkHandler: NetworkHandler by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(movieDBModule))

        Timber.plant(DebugTree())
        networkHandler.downloadConfigData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { jsonElement ->
                    networkHandler.initializeMovieDB(
                            jsonElement.asJsonObject.get("images").asJsonObject.get("base_url").asString)
                }
    }
}
