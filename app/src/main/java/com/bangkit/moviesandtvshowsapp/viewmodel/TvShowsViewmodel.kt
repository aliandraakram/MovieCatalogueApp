package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowapp.core.vo.Resource

class TvShowsViewmodel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getTvShows(): LiveData<Resource<List<TvShow>>> =
        movieTvShowUseCase.getAllTvShows().asLiveData()
}