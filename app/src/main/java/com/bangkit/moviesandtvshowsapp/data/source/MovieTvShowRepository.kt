package com.bangkit.moviesandtvshowsapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bangkit.moviesandtvshowsapp.data.NetworkBoundResources
import com.bangkit.moviesandtvshowsapp.data.source.local.LocalDataSource
import com.bangkit.moviesandtvshowsapp.domain.repository.MovieTvShowDataSource
import com.bangkit.moviesandtvshowsapp.data.source.remote.RemoteDataSource
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.ApiResponse
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity
import com.bangkit.moviesandtvshowsapp.dataclass.response.*
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.utils.AppExecutors
import com.bangkit.moviesandtvshowsapp.utils.DataMapper
import com.bangkit.moviesandtvshowsapp.vo.Resource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieTvShowRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieTvShowDataSource {

    companion object {
        @Volatile
        private var instance: MovieTvShowRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            local: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieTvShowRepository =
            instance ?: synchronized(this) {
                instance
                    ?: MovieTvShowRepository(remoteData, local, appExecutors).apply {
                        instance = this
                    }
            }

        const val key = "30da08c5c31b0203e6d13b9425c272b3"
    }

    override fun getAllMovies(): Flowable<Resource<List<Movie>>> {
        return object :
            NetworkBoundResources<List<Movie>, List<ResultsMovies>>(appExecutors) {
            public override fun loadFromDB(): Flowable<List<Movie>> {
              return localDataSource.getMovieList().map { DataMapper.listMovieEntityToMovie(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): Flowable<ApiResponse<List<ResultsMovies>>> =
                remoteDataSource.getMovieList(key)

            override fun saveCallResult(data: List<ResultsMovies>) {
                val list = DataMapper.movieListToEntity(data)
                localDataSource.insertMovieList(list)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getAllTvShows(): Flowable<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResources<List<TvShow>, List<ResultsTvShows>>(appExecutors) {
            public override fun loadFromDB(): Flowable<List<TvShow>> {
                return localDataSource.getTvShowList().map {
                    DataMapper.listShowEntitytoTvShow(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): Flowable<ApiResponse<List<ResultsTvShows>>> =
                remoteDataSource.getTvShowList(key)

            public override fun saveCallResult(data: List<ResultsTvShows>) {
                val list = DataMapper.showListToEntity(data)
                localDataSource.insertTvShowList(list)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getMovie(id: Int): Flowable<Resource<Movie>> {
        return object : NetworkBoundResources<Movie, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): Flowable<Movie> =
                localDataSource.getMovie(id).map { DataMapper.movieEntityToMovie(it) }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.genre == " "

            override fun createCall(): Flowable<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovie(id, key)

            override fun saveCallResult(data: MovieDetailResponse) {
                val result = DataMapper.movieDetailResponseToEntity(data)
                localDataSource.updateMovie(result, false)
            }
        }.asFlowable()
    }

    override fun getTvShow(id: Int): Flowable<Resource<TvShow>> {
        return object : NetworkBoundResources<TvShow, TvShowDetailResponse>(appExecutors) {
            public override fun loadFromDB(): Flowable<TvShow> =
                localDataSource.getTvShow(id).map {
                    DataMapper.tvShowEntitytoTvShow(it)
                }

            override fun shouldFetch(data: TvShow?): Boolean =
                data?.duration == " "

            public override fun createCall(): Flowable<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShow(id, key)

            public override fun saveCallResult(data: TvShowDetailResponse) {
                val result = DataMapper.showDetailResponseToEntity(data)
                localDataSource.updateTvShow(result, false)
            }
        }.asFlowable()
    }

    override fun getFavMovie(): Flowable<List<Movie>> {
        return localDataSource.getFavMovie().map{
            DataMapper.listMovieEntityToMovie(it)
        }
    }

    override fun getFavTvShow(): Flowable<List<TvShow>> {
        return localDataSource.getFavTvShow().map {
            DataMapper.listShowEntitytoTvShow(it)
        }
    }

    override fun setFavMovie(movies: Movie, favorited: Boolean) {
        val movie = DataMapper.movieToMovieEntity(movies)
        appExecutors.diskIO().execute { localDataSource.updateMovie(movie, favorited) }
    }

    override fun setFavTvShow(tvShows: TvShow, favorited: Boolean) {
        val show = DataMapper.tvShowToTvShowEntity(tvShows)
        appExecutors.diskIO().execute { localDataSource.updateTvShow(show, favorited) }
    }


}