package com.bangkit.moviesandtvshowsapp.utils

import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity
import com.bangkit.moviesandtvshowsapp.dataclass.response.*
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bangkit.moviesandtvshowsapp.domain.model.TvShow

object DataMapper {
    fun movieListToEntity(data: List<ResultsMovies>): List<MoviesEntity> =
        data.map {
            MoviesEntity(
                it.id,
                it.posterPath,
                it.title,
                it.releaseDate,
                it.overview,
                it.voteAverage,
                " ",
                " ",
                false
            )
        }

    fun showListToEntity(data: List<ResultsTvShows>): List<TvShowsEntity> =
        data.map {
            TvShowsEntity(
                it.id,
                it.posterPath,
                it.name,
                it.firstAirDate,
                0,
                " ",
                0.0,
                " ",
                it.overview,
                false
            )
        }

    fun movieDetailResponseToEntity(data: MovieDetailResponse): MoviesEntity =
        MoviesEntity(data.id,
                data.posterPath,
                data.title,
                data.releaseDate,
                data.overview,
                data.voteAverage,
                getGenre(data.genres),
                data.runtime.toString(),
                false
            )

    fun showDetailResponseToEntity(data: TvShowDetailResponse): TvShowsEntity =
        TvShowsEntity(data.id,
            data.posterPath,
            data.name,
            data.firstAirDate,
            data.numberOfSeasons,
            getGenre(data.genres),
            data.voteAverage,
            data.episodeRunTime[0].toString(),
            data.overview,
            false
        )

    fun listMovieEntityToMovie(data: List<MoviesEntity>): List<Movie> =
        data.map {
            Movie(it.movId,
                it.image,
                it.title,
                it.date,
                it.description,
                it.rating,
                it.genre,
                it.duration,
                it.isFavorited
                )
        }

    fun listShowEntitytoTvShow(data: List<TvShowsEntity>): List<TvShow> =
        data.map {
            TvShow(it.tvId,
                it.image,
                it.title,
                it.date,
                it.seasons,
                it.genre,
                it.rating,
                it.duration,
                it.description,
                it.isFavorited
                )
        }

    fun movieToMovieEntity(data: Movie): MoviesEntity =
        MoviesEntity(data.movId,
                data.image,
                data.title,
                data.date,
                data.description,
                data.rating,
                data.genre,
                data.duration,
                data.isFavorited
            )

    fun tvShowToTvShowEntity(data: TvShow) : TvShowsEntity =
        TvShowsEntity(data.tvId,
            data.image,
            data.title,
            data.date,
            data.seasons,
            data.genre,
            data.rating,
            data.duration,
            data.description,
            data.isFavorited
            )

    fun movieEntityToMovie(data: MoviesEntity): Movie =
        Movie(data.movId,
            data.image,
            data.title,
            data.date,
            data.description,
            data.rating,
            data.genre,
            data.duration,
            data.isFavorited)

    fun tvShowEntitytoTvShow(data:TvShowsEntity): TvShow =
        TvShow(data.tvId,
            data.image,
            data.title,
            data.date,
            data.seasons,
            data.genre,
            data.rating,
            data.duration,
            data.description,
            data.isFavorited)

    fun getGenre(list: List<GenresItem>): String{
        var genre = ""

        for (i in list.indices){
            genre += "${list[i].name}, "
        }

        return genre
    }
}