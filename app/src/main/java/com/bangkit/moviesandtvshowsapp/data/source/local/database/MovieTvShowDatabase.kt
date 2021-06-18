package com.bangkit.moviesandtvshowsapp.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity


@Database(
    entities = [MoviesEntity::class, TvShowsEntity::class],
    version = 1, exportSchema = false
)
abstract class MovieTvShowDatabase : RoomDatabase() {

    abstract fun movieTvShowDao(): Dao

    companion object {
        @Volatile

        private var INSTANCE: MovieTvShowDatabase? = null

        fun getInstance(context: Context): MovieTvShowDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieTvShowDatabase::class.java,
                    "MovieTvShow.db"
                ).build().apply {
                    INSTANCE = this
                }
            }

    }
}