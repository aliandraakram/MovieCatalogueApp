package com.bangkit.moviesandtvshowapp.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit.moviesandtvshowapp.core.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowapp.core.dataclass.entity.TvShowsEntity


@Database(
    entities = [MoviesEntity::class, TvShowsEntity::class],
    version = 1, exportSchema = false
)
abstract class MovieTvShowDatabase : RoomDatabase() {

    abstract fun movieTvShowDao(): Dao


}