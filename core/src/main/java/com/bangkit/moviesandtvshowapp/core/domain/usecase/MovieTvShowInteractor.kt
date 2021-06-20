package com.bangkit.moviesandtvshowapp.core.domain.usecase

import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.domain.repository.MovieTvShowDataSource
import com.bangkit.moviesandtvshowapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieTvShowInteractor(private val repo: MovieTvShowDataSource) : MovieTvShowUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        repo.getAllMovies()

    override fun getAllTvShows(): Flow<Resource<List<TvShow>>> =
        repo.getAllTvShows()

    override fun getMovie(id: Int): Flow<Resource<Movie>> =
        repo.getMovie(id)

    override fun getTvShow(id: Int): Flow<Resource<TvShow>> =
        repo.getTvShow(id)

    override fun getFavMovie(): Flow<List<Movie>> =
        repo.getFavMovie()

    override fun getFavTvShow(): Flow<List<TvShow>> =
        repo.getFavTvShow()

    override fun setFavMovie(movies: Movie, favorited: Boolean) =
        repo.setFavMovie(movies, favorited)

    override fun setFavTvShow(tvShows: TvShow, favorited: Boolean) =
        repo.setFavTvShow(tvShows, favorited)

}