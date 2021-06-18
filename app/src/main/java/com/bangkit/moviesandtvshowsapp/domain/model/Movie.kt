package com.bangkit.moviesandtvshowsapp.domain.model

data class Movie (
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