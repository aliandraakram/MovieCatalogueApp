package com.bangkit.moviesandtvshowapp.core.domain.repository

import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieTvShowDataSource {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getAllTvShows(): Flow<Resource<List<TvShow>>>

    fun getMovie(id: Int): Flow<Resource<Movie>>

    fun getTvShow(id: Int): Flow<Resource<TvShow>>

    fun getFavMovie(): Flow<List<Movie>>

    fun getFavTvShow(): Flow<List<TvShow>>

    fun setFavMovie(movies: Movie, favorited: Boolean)

    fun setFavTvShow(tvShows: TvShow, favorited: Boolean)
}