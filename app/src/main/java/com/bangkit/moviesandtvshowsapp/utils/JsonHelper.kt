package com.bangkit.moviesandtvshowsapp.utils

import android.content.Context
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.MovieResponse
import com.bangkit.moviesandtvshowsapp.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovieList(): List<MovieResponse> {
        val list = mutableListOf<MovieResponse>()
        try {
            val responseObject =
                JSONObject(parsingFileToString("MovieListResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movieItem = listArray.getJSONObject(i)

                val id = movieItem.getInt("id")
                val description = movieItem.getString("overview")
                val imagePath = movieItem.getString("poster_path")
                val date = movieItem.getString("release_date")
                val title = movieItem.getString("title")
                val rating = movieItem.getDouble("vote_average")
                val genre = movieItem.getString("genres")
                val duration = movieItem.getInt("runtime")

                val movieResponse = MovieResponse(
                    id,
                    imagePath,
                    title,
                    date,
                    description,
                    rating,
                    genre,
                    duration.toString()
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShowList(): List<TvShowResponse> {
        val list = mutableListOf<TvShowResponse>()
        try {
            val responseObject =
                JSONObject(parsingFileToString("TvShowListResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShowItem = listArray.getJSONObject(i)

                val date = tvShowItem.getString("first_air_date")
                val id = tvShowItem.getInt("id")
                val title = tvShowItem.getString("name")
                val description = tvShowItem.getString("overview")
                val imagePath = tvShowItem.getString("poster_path")
                val seasonNumber = tvShowItem.getInt("number_of_seasons")
                val rating = tvShowItem.getDouble("vote_average")
                val genre = tvShowItem.getString("genres")
                val duration = tvShowItem.getInt("episode_run_time")

                val tvShowResponse = TvShowResponse(
                    id,
                    imagePath,
                    title,
                    date,
                    seasonNumber,
                    genre,
                    rating,
                    duration.toString(),
                    description
                )
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

}