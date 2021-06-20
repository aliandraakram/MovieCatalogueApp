package com.bangkit.moviesandtvshowapp.core.data.source.local

import com.bangkit.moviesandtvshowapp.core.data.source.local.database.Dao
import com.bangkit.moviesandtvshowapp.core.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowapp.core.dataclass.entity.TvShowsEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val movieTvShowDao: Dao) {


    fun getMovieList(): Flow<List<MoviesEntity>> =
        movieTvShowDao.getMovieList()

    fun getTvShowList(): Flow<List<TvShowsEntity>> =
        movieTvShowDao.getTvShowList()

    fun getMovie(id: Int): Flow<MoviesEntity> = movieTvShowDao.getMovie(id)

    fun getTvShow(id: Int): Flow<TvShowsEntity> = movieTvShowDao.getTvShow(id)

    fun getFavMovie(): Flow<List<MoviesEntity>> =
        movieTvShowDao.getFavMovie()

    fun getFavTvShow(): Flow<List<TvShowsEntity>> =
        movieTvShowDao.getFavTvShow()


    fun updateMovie(moviesEntity: MoviesEntity, favorited: Boolean) {
        moviesEntity.isFavorited = favorited
        movieTvShowDao.updateMovie(moviesEntity)
    }

    fun updateTvShow(tvShowsEntity: TvShowsEntity, favorited: Boolean) {
        tvShowsEntity.isFavorited = favorited
        movieTvShowDao.updateTvShow(tvShowsEntity)
    }

    suspend fun insertMovieList(list: List<MoviesEntity>) = movieTvShowDao.setMovies(list)

    suspend fun insertTvShowList(list: List<TvShowsEntity>) = movieTvShowDao.setTvShows(list)

}