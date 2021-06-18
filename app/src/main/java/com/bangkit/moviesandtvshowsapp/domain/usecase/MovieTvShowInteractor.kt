package com.bangkit.moviesandtvshowsapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.domain.repository.MovieTvShowDataSource
import com.bangkit.moviesandtvshowsapp.vo.Resource
import io.reactivex.Flowable

class MovieTvShowInteractor(private val repo: MovieTvShowDataSource) : MovieTvShowUseCase {
    override fun getAllMovies(): Flowable<Resource<List<Movie>>> =
        repo.getAllMovies()

    override fun getAllTvShows(): Flowable<Resource<List<TvShow>>> =
        repo.getAllTvShows()

    override fun getMovie(id: Int): Flowable<Resource<Movie>> =
        repo.getMovie(id)

    override fun getTvShow(id: Int): Flowable<Resource<TvShow>> =
        repo.getTvShow(id)

    override fun getFavMovie(): Flowable<List<Movie>> =
        repo.getFavMovie()

    override fun getFavTvShow(): Flowable<List<TvShow>> =
        repo.getFavTvShow()

    override fun setFavMovie(movies: Movie, favorited: Boolean) =
        repo.setFavMovie(movies, favorited)

    override fun setFavTvShow(tvShows: TvShow, favorited: Boolean) =
        repo.setFavTvShow(tvShows, favorited)

}