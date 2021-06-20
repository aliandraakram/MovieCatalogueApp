package com.bangkit.moviesandtvshowapp.core.data.source.local.database

import androidx.room.*
import androidx.room.Dao
import com.bangkit.moviesandtvshowapp.core.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowapp.core.dataclass.entity.TvShowsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM movie")
    fun getMovieList(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM tvShow")
    fun getTvShowList(): Flow<List<TvShowsEntity>>

    @Query("SELECT * FROM movie WHERE movId = :id")
    fun getMovie(id: Int): Flow<MoviesEntity>

    @Query("SELECT * FROM tvShow WHERE tvId = :id")
    fun getTvShow(id: Int): Flow<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setMovies(movie: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setTvShows(tvShowEntity: List<TvShowsEntity>)

    @Query("SELECT * FROM movie WHERE isFavorited = 1 ")
    fun getFavMovie(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM tvShow WHERE isFavorited = 1")
    fun getFavTvShow(): Flow<List<TvShowsEntity>>

    @Update
    fun updateMovie(moviesEntity: MoviesEntity)

    @Update
    fun updateTvShow(tvShowsEntity: TvShowsEntity)


}