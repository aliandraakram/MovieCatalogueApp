package com.bangkit.moviesandtvshowsapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.vo.Resource
import io.reactivex.Flowable

interface MovieTvShowUseCase {
    fun getAllMovies(): Flowable<Resource<List<Movie>>>

    fun getAllTvShows(): Flowable<Resource<List<TvShow>>>

    fun getMovie(id: Int): Flowable<Resource<Movie>>

    fun getTvShow(id: Int): Flowable<Resource<TvShow>>

    fun getFavMovie(): Flowable<List<Movie>>

    fun getFavTvShow(): Flowable<List<TvShow>>

    fun setFavMovie(movies: Movie, favorited: Boolean)

    fun setFavTvShow(tvShows: TvShow, favorited: Boolean)
}