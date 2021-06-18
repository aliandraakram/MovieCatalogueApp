package com.bangkit.moviesandtvshowsapp.dataclass.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowsEntity(
    @PrimaryKey
    var tvId: Int,
    var image: String,
    var title: String,
    var date: String,
    var seasons: Int,
    var genre: String,
    var rating: Double,
    var duration: String,
    var description: String,
    var isFavorited: Boolean
)