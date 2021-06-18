package com.bangkit.moviesandtvshowsapp.data.source.remote.response.api

import com.bangkit.moviesandtvshowsapp.data.source.remote.response.MovieResponse
import com.bangkit.moviesandtvshowsapp.dataclass.response.MovieDetailResponse
import com.bangkit.moviesandtvshowsapp.dataclass.response.MovieListResponse
import com.bangkit.moviesandtvshowsapp.dataclass.response.TvShowDetailResponse
import com.bangkit.moviesandtvshowsapp.dataclass.response.TvShowListResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovieList(@Query("api_key") apiKey: String): Flowable<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Flowable<MovieDetailResponse>

    @GET("tv/popular")
    fun getTvShowList(@Query("api_key") apiKey: String): Flowable<TvShowListResponse>

    @GET("tv/{tv_id}")
    fun getDetailShow(@Path("tv_id") id: Int, @Query("api_key") key: String): Flowable<TvShowDetailResponse>
}