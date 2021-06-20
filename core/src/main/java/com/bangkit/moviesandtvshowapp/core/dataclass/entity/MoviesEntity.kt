package com.bangkit.moviesandtvshowapp.core.dataclass.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
data class MoviesEntity(
    @PrimaryKey
    var movId: Int,
    var image: String,
    var title: String,
    var date: String,
    var description: String,
    var rating: Double,
    var genre: String,
    var duration: String,
    var isFavorited: Boolean
)