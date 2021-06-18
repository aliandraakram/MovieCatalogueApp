package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bangkit.moviesandtvshowsapp.data.source.MovieTvShowRepository
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowsapp.vo.Resource

class TvShowsViewmodel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getTvShows(): LiveData<Resource<List<TvShow>>> = LiveDataReactiveStreams.fromPublisher(movieTvShowUseCase.getAllTvShows())
}