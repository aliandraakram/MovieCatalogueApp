package com.bangkit.moviesandtvshowapp.core.dataclass.response

import com.google.gson.annotations.SerializedName

data class TvShowListResponse(


    @field:SerializedName("results")
    val results: List<ResultsTvShows>,


    )

data class ResultsTvShows(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    )
