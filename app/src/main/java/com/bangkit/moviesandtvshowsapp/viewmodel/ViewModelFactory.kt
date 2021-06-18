package com.bangkit.moviesandtvshowsapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.moviesandtvshowsapp.Injection
import com.bangkit.moviesandtvshowsapp.data.source.MovieTvShowRepository
import com.bangkit.moviesandtvshowsapp.domain.usecase.MovieTvShowUseCase

class ViewModelFactory private constructor(private val mMovieTvShowUseCase: MovieTvShowUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.getUseCase(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewmodel::class.java) -> {
                return MovieViewmodel(mMovieTvShowUseCase) as T
            }
            modelClass.isAssignableFrom(TvShowsViewmodel::class.java) -> {
                return TvShowsViewmodel(mMovieTvShowUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMovieTvShowUseCase) as T
            }
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                return FavMovieViewModel(mMovieTvShowUseCase) as T
            }
            modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                return FavTvShowViewModel(mMovieTvShowUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}