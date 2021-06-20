package com.bangkit.moviesandtvshowapp.core.data.source.remote.response

data class MovieResponse(
    var movId: Int,
    var image: String,
    var title: String,
    var date: String,
    var description: String,
    var rating: Double,
    var genre: String,
    var duration: String,
)
