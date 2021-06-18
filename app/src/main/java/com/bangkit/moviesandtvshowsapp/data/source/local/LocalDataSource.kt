package com.bangkit.moviesandtvshowsapp.data.source.local

import android.text.method.MovementMethod
import androidx.lifecycle.LiveData
import com.bangkit.moviesandtvshowsapp.data.source.local.database.Dao
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity
import io.reactivex.Flowable

class LocalDataSource private constructor(private val movieTvShowDao: Dao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao)
    }

    fun getMovieList(): Flowable<List<MoviesEntity>> =
        movieTvShowDao.getMovieList()

    fun getTvShowList(): Flowable<List<TvShowsEntity>> =
        movieTvShowDao.getTvShowList()

    fun getMovie(id: Int): Flowable<MoviesEntity> = movieTvShowDao.getMovie(id)

    fun getTvShow(id: Int): Flowable<TvShowsEntity> = movieTvShowDao.getTvShow(id)

    fun getFavMovie(): Flowable<List<MoviesEntity>> =
        movieTvShowDao.getFavMovie()

    fun getFavTvShow(): Flowable<List<TvShowsEntity>> =
        movieTvShowDao.getFavTvShow()


    fun updateMovie(moviesEntity: MoviesEntity, favorited: Boolean) {
        moviesEntity.isFavorited = favorited
        movieTvShowDao.updateMovie(moviesEntity)
    }

    fun updateTvShow(tvShowsEntity: TvShowsEntity, favorited: Boolean) {
        tvShowsEntity.isFavorited = favorited
        movieTvShowDao.updateTvShow(tvShowsEntity)
    }

    fun insertMovieList(list: List<MoviesEntity>) = movieTvShowDao.setMovies(list)

    fun insertTvShowList(list: List<TvShowsEntity>) = movieTvShowDao.setTvShows(list)

}