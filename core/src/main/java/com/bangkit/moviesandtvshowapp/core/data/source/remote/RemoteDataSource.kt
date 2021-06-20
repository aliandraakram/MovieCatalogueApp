package com.bangkit.moviesandtvshowapp.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.bangkit.moviesandtvshowapp.core.data.source.remote.response.ApiResponse
import com.bangkit.moviesandtvshowapp.core.data.source.remote.response.api.ApiService
import com.bangkit.moviesandtvshowapp.core.dataclass.response.MovieDetailResponse
import com.bangkit.moviesandtvshowapp.core.dataclass.response.ResultsMovies
import com.bangkit.moviesandtvshowapp.core.dataclass.response.ResultsTvShows
import com.bangkit.moviesandtvshowapp.core.dataclass.response.TvShowDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val api: ApiService) {



    @SuppressLint("CheckResult")
    fun getMovieList(key: String): Flow<ApiResponse<List<ResultsMovies>>> {
        return flow {
            try {
                val response = api.getMovieList(key)
                val result = response.results
                if (result.isNotEmpty()){
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d("Remote Source", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("CheckResult")
    fun getTvShowList(key: String): Flow<ApiResponse<List<ResultsTvShows>>> {
        return flow {
            try {
                val response = api.getTvShowList(key)
                val result = response.results
                if (result.isNotEmpty()){
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d("Remote Source", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("CheckResult")
    fun getMovie(id: Int, key: String): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = api.getDetailMovie(id, key)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d("Remote Source", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("CheckResult")
    fun getTvShow(id: Int, key: String): Flow<ApiResponse<TvShowDetailResponse>> {
        return flow {
            try {
                val response = api.getDetailShow(id, key)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d("Remote Source", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}