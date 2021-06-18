package com.bangkit.moviesandtvshowsapp.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.concurrent.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM movie")
    fun getMovieList(): Flowable<List<MoviesEntity>>

    @Query("SELECT * FROM tvShow")
    fun getTvShowList(): Flowable<List<TvShowsEntity>>

    @Query("SELECT * FROM movie WHERE movId = :id")
    fun getMovie(id: Int): Flowable<MoviesEntity>

    @Query("SELECT * FROM tvShow WHERE tvId = :id")
    fun getTvShow(id: Int): Flowable<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(movie: List<MoviesEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTvShows(tvShowEntity: List<TvShowsEntity>): Completable

    @Query("SELECT * FROM movie WHERE isFavorited = 1 ")
    fun getFavMovie(): Flowable<List<MoviesEntity>>

    @Query("SELECT * FROM tvShow WHERE isFavorited = 1")
    fun getFavTvShow(): Flowable<List<TvShowsEntity>>

    @Update
    fun updateMovie(moviesEntity: MoviesEntity)

    @Update
    fun updateTvShow(tvShowsEntity: TvShowsEntity)


}