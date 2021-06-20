package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowUseCase

class FavMovieViewModel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getFavMovie(): LiveData<List<Movie>> =
        movieTvShowUseCase.getFavMovie().asLiveData()
}