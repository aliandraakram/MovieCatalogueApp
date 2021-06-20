package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowapp.core.vo.Resource

class MovieViewmodel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> =
        movieTvShowUseCase.getAllMovies().asLiveData()
}