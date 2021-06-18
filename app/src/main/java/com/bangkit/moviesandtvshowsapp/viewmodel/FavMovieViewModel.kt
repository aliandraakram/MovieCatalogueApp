package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bangkit.moviesandtvshowsapp.data.source.MovieTvShowRepository
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.usecase.MovieTvShowUseCase

class FavMovieViewModel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getFavMovie(): LiveData<List<Movie>> = LiveDataReactiveStreams.fromPublisher(movieTvShowUseCase.getFavMovie())
}