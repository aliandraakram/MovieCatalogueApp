package com.bangkit.moviesandtvshowapp.core.dataclass.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(


    @field:SerializedName("results")
    val results: List<ResultsMovies>,

    )

data class ResultsMovies(

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,


    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,


    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,

    )
