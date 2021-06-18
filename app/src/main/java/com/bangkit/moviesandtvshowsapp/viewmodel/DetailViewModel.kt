package com.bangkit.moviesandtvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.bangkit.moviesandtvshowsapp.data.source.MovieTvShowRepository
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowsapp.vo.Resource

class DetailViewModel(private val movieTvShowUseCase: MovieTvShowUseCase) : ViewModel() {

    fun getSelectedMovies(idMovie: Int): LiveData<Resource<Movie>> =
        LiveDataReactiveStreams.fromPublisher(movieTvShowUseCase.getMovie(idMovie))

    fun getSelectedShows(idShows: Int): LiveData<Resource<TvShow>> =
        LiveDataReactiveStreams.fromPublisher(movieTvShowUseCase.getTvShow(idShows))

    fun setFavMovie(moviesEntity: Movie, favorited: Boolean) =
        movieTvShowUseCase.setFavMovie(moviesEntity, favorited)

    fun setFavTvShow(tvShowsEntity: TvShow, favorited: Boolean) =
        movieTvShowUseCase.setFavTvShow(tvShowsEntity, favorited)

}