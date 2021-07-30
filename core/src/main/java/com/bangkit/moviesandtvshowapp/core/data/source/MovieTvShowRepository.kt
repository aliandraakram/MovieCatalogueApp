package com.bangkit.moviesandtvshowapp.core.data.source

import android.util.Log
import com.bangkit.moviesandtvshowapp.core.data.NetworkBoundResources
import com.bangkit.moviesandtvshowapp.core.data.source.local.LocalDataSource
import com.bangkit.moviesandtvshowapp.core.data.source.remote.RemoteDataSource
import com.bangkit.moviesandtvshowapp.core.data.source.remote.response.ApiResponse
import com.bangkit.moviesandtvshowapp.core.dataclass.response.MovieDetailResponse
import com.bangkit.moviesandtvshowapp.core.dataclass.response.ResultsMovies
import com.bangkit.moviesandtvshowapp.core.dataclass.response.ResultsTvShows
import com.bangkit.moviesandtvshowapp.core.dataclass.response.TvShowDetailResponse
import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.domain.repository.MovieTvShowDataSource
import com.bangkit.moviesandtvshowapp.core.utils.AppExecutors
import com.bangkit.moviesandtvshowapp.core.utils.DataMapper
import com.bangkit.moviesandtvshowapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieTvShowRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieTvShowDataSource {

    companion object {

        const val key = "30da08c5c31b0203e6d13b9425c272b3"
    }

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResources<List<Movie>, List<ResultsMovies>>(appExecutors) {
            public override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovieList().map { DataMapper.listMovieEntityToMovie(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovies>>> =
                remoteDataSource.getMovieList(key)

            override suspend fun saveCallResult(data: List<ResultsMovies>) {
                Log.d("onMovieListRepo", data.toString())
                val list = DataMapper.movieListToEntity(data)
                localDataSource.insertMovieList(list)
            }
        }.asFlow()
    }

    override fun getAllTvShows(): Flow<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResources<List<TvShow>, List<ResultsTvShows>>(appExecutors) {
            public override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getTvShowList().map {
                    DataMapper.listShowEntitytoTvShow(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTvShows>>> =
                remoteDataSource.getTvShowList(key)

            override suspend fun saveCallResult(data: List<ResultsTvShows>) {
                Log.d("onShowListRepo", data.toString())
                val list = DataMapper.showListToEntity(data)
                localDataSource.insertTvShowList(list)
            }
        }.asFlow()
    }

    override fun getMovie(id: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResources<Movie, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Movie> =
                localDataSource.getMovie(id).map { DataMapper.movieEntityToMovie(it) }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.genre == " "

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovie(id, key)

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                Log.d("onMovieRepo", data.toString())
                val result = DataMapper.movieDetailResponseToEntity(data)
                appExecutors.diskIO().execute { localDataSource.updateMovie(result, false) }

            }
        }.asFlow()
    }

    override fun getTvShow(id: Int): Flow<Resource<TvShow>> {
        return object : NetworkBoundResources<TvShow, TvShowDetailResponse>(appExecutors) {
            public override fun loadFromDB(): Flow<TvShow> =
                localDataSource.getTvShow(id).map {
                    DataMapper.tvShowEntitytoTvShow(it)
                }

            override fun shouldFetch(data: TvShow?): Boolean =
                data?.duration == " "

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShow(id, key)

            override suspend fun saveCallResult(data: TvShowDetailResponse) {
                Log.d("onShowRepo", data.toString())
                val result = DataMapper.showDetailResponseToEntity(data)
                appExecutors.diskIO().execute { localDataSource.updateTvShow(result, false) }
            }
        }.asFlow()
    }

    override fun getFavMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMovie().map {
            DataMapper.listMovieEntityToMovie(it)
        }
    }

    override fun getFavTvShow(): Flow<List<TvShow>> {
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