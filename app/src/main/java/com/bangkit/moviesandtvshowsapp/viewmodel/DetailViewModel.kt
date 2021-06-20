package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowapp.core.vo.Resource

class DetailViewModel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getSelectedMovies(idMovie: Int): LiveData<Resource<Movie>> =
        movieTvShowUseCase.getMovie(idMovie).asLiveData()

    fun getSelectedShows(idShows: Int): LiveData<Resource<TvShow>> =
        movieTvShowUseCase.getTvShow(idShows).asLiveData()

    fun setFavMovie(moviesEntity: Movie, favorited: Boolean) =
        movieTvShowUseCase.setFavMovie(moviesEntity, favorited)

    fun setFavTvShow(tvShowsEntity: TvShow, favorited: Boolean) =
        movieTvShowUseCase.setFavTvShow(tvShowsEntity, favorited)

}