package com.bangkit.moviesandtvshowsapp.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowUseCase

class FavTvShowViewModel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getFavTvShow(): LiveData<List<TvShow>> =
        movieTvShowUseCase.getFavTvShow().asLiveData()
}