package com.bangkit.moviesandtvshowsapp.data.source.remote.response


data class TvShowResponse(
    var tvId: Int,
    var image: String,
    var title: String,
    var date: String,
    var seasons: Int,
    var genre: String,
    var rating: Double,
    var duration: String,
    var description: String,
)
