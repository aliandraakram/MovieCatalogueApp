package com.bangkit.moviesandtvshowsapp.data.source.remote

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.ApiResponse
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.MovieResponse
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.TvShowResponse
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.api.ApiService
import com.bangkit.moviesandtvshowsapp.dataclass.response.*
import com.bangkit.moviesandtvshowsapp.utils.EspressoIdlingResources
import com.bangkit.moviesandtvshowsapp.utils.JsonHelper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val api: ApiService) {


    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service).apply { instance = this }
            }
    }

    @SuppressLint("CheckResult")
    fun getMovieList(key: String): Flowable<ApiResponse<List<ResultsMovies>>> {
        val resultMovies = PublishSubject.create<ApiResponse<List<ResultsMovies>>>()

        val result = api.getMovieList(key)

        EspressoIdlingResources.increment()
        result
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val data = response.results
                resultMovies.onNext(if (data.isNotEmpty()) ApiResponse.Success(data) else ApiResponse.Empty)
            }, { error ->
                resultMovies.onNext(ApiResponse.Error(error.message.toString()))
                Log.d("Remote Movie List", error.toString())
            })

        return resultMovies.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getTvShowList(key: String): Flowable<ApiResponse<List<ResultsTvShows>>> {
        val resultShows = PublishSubject.create<ApiResponse<List<ResultsTvShows>>>()

        val result = api.getTvShowList(key)

        result
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe( { response ->
                val array = response.results
                resultShows.onNext(if (array.isNotEmpty()) ApiResponse.Success(array) else ApiResponse.Empty)
            }, {error ->
                resultShows.onNext(ApiResponse.Error(error.message.toString()))
                Log.d("Remote ShowList", error.toString())
            })


        return resultShows.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getMovie(id: Int, key: String): Flowable<ApiResponse<MovieDetailResponse>> {
        val resultMovies = PublishSubject.create<ApiResponse<MovieDetailResponse>>()

        val result = api.getDetailMovie(id, key)

        result
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe( { response ->
                resultMovies.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, {error ->
                resultMovies.onNext(ApiResponse.Error(error.message.toString()))
                Log.d("Remote MovieDetail", error.toString())
            })

        return resultMovies.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getTvShow(id: Int, key: String): Flowable<ApiResponse<TvShowDetailResponse>> {
        val resultShow = PublishSubject.create<ApiResponse<TvShowDetailResponse>>()

        val result = api.getDetailShow(id, key)

        result
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe( { response ->
                resultShow.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, {error ->
                resultShow.onNext(ApiResponse.Error(error.message.toString()))
                Log.d("Remote ShowDetailt", error.toString())
            })

        return resultShow.toFlowable(BackpressureStrategy.BUFFER)
    }

}