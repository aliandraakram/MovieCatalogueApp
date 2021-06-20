package com.bangkit.moviesandtvshowapp.core.data.source.remote.response.api

import com.bangkit.moviesandtvshowapp.core.dataclass.response.MovieDetailResponse
import com.bangkit.moviesandtvshowapp.core.dataclass.response.MovieListResponse
import com.bangkit.moviesandtvshowapp.core.dataclass.response.TvShowDetailResponse
import com.bangkit.moviesandtvshowapp.core.dataclass.response.TvShowListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey: String): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): MovieDetailResponse

    @GET("tv/popular")
    suspend fun getTvShowList(@Query("api_key") apiKey: String): TvShowListResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailShow(@Path("tv_id") id: Int, @Query("api_key") key: String): TvShowDetailResponse
}