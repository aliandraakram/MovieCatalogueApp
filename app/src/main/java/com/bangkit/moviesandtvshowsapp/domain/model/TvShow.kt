package com.bangkit.moviesandtvshowsapp.domain.model

data class TvShow(
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
