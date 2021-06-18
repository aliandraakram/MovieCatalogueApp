package com.bangkit.moviesandtvshowsapp

import android.content.Context
import com.bangkit.moviesandtvshowsapp.data.source.MovieTvShowRepository
import com.bangkit.moviesandtvshowsapp.data.source.local.LocalDataSource
import com.bangkit.moviesandtvshowsapp.data.source.local.database.MovieTvShowDatabase
import com.bangkit.moviesandtvshowsapp.data.source.remote.RemoteDataSource
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.api.ApiConfig
import com.bangkit.moviesandtvshowsapp.domain.repository.MovieTvShowDataSource
import com.bangkit.moviesandtvshowsapp.domain.usecase.MovieTvShowInteractor
import com.bangkit.moviesandtvshowsapp.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowsapp.utils.AppExecutors
import com.bangkit.moviesandtvshowsapp.utils.JsonHelper

object Injection {
    fun provideRepo(context: Context): MovieTvShowDataSource {
        val db = MovieTvShowDatabase.getInstance(context)
        val local = LocalDataSource.getInstance(db.movieTvShowDao())
        val appExe = AppExecutors()

        val remoteData = RemoteDataSource.getInstance(ApiConfig.getApi())
        return MovieTvShowRepository.getInstance(remoteData, local, appExe)
    }

    fun getUseCase(context: Context): MovieTvShowUseCase{
        val repo = provideRepo(context)
        return MovieTvShowInteractor(repo)
    }
}