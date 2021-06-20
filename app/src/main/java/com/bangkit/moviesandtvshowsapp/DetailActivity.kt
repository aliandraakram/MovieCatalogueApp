package com.bangkit.moviesandtvshowsapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.moviesandtvshowapp.core.domain.model.Movie
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowapp.core.vo.Status
import com.bangkit.moviesandtvshowsapp.databinding.ActivityDetailBinding
import com.bangkit.moviesandtvshowsapp.databinding.ContentDetailBinding
import com.bangkit.moviesandtvshowsapp.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ContentDetailBinding
    private lateinit var favMovie: Movie
    private lateinit var favTvShowsEntity: TvShow


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding = ActivityDetailBinding.inflate(layoutInflater)
        binding = activityBinding.detailLayout
        setContentView(activityBinding.root)

        setSupportActionBar(activityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(ID, 0)
        val identifier = intent.getIntExtra(IDENTIFIER, 0)

        getDetail(id, identifier)

        binding.btnFav.setOnClickListener {
            getDetail(id, identifier)
            when (identifier) {
                ID_MOVIE -> {
                    if (favMovie.isFavorited) {
                        viewModel.setFavMovie(favMovie, false)
                        binding.btnFav.setImageResource(R.drawable.ic_favorite_border)
                    } else {
                        viewModel.setFavMovie(favMovie, true)
                        binding.btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                }
                ID_SHOW -> {
                    if (favTvShowsEntity.isFavorited) {
                        viewModel.setFavTvShow(favTvShowsEntity, false)
                        binding.btnFav.setImageResource(R.drawable.ic_favorite_border)
                    } else {
                        viewModel.setFavTvShow(favTvShowsEntity, true)
                    }
                }
            }
        }
    }

    private fun getDetail(id: Int, identifier: Int) {
        when (identifier) {
            ID_MOVIE -> {
                viewModel.getSelectedMovies(id).observe(this, {
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> binding.progBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progBar.visibility = View.INVISIBLE
                                attachMovie(it.data as Movie)
                            }
                            Status.ERROR -> {
                                binding.progBar.visibility = View.VISIBLE
                                Toast.makeText(this, "Movie detail is empty", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                    title = resources.getString(R.string.detail_movie_title)
                })
            }
            ID_SHOW -> {
                viewModel.getSelectedShows(id).observe(this, {
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> binding.progBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progBar.visibility = View.INVISIBLE
                                attachTvShow(it.data as TvShow)
                            }
                            Status.ERROR -> {
                                binding.progBar.visibility = View.INVISIBLE
                                Toast.makeText(this, "Show detail is empty", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                    }
                    title = resources.getString(R.string.detail_show_title)
                })
            }
            else -> Toast.makeText(this, "id not recognize", Toast.LENGTH_LONG).show()
        }
    }

    private fun attachMovie(movie: Movie) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.image}")
            .into(binding.detailImg)

        binding.apply {
            detailTitle.text = movie.title
            detailDate.text = movie.date
            detailGenre.text = movie.genre
            detailDesc.text = movie.description
            detailDuration.text = resources.getString(R.string.duration_value, movie.duration)
            detailUserScore.text = movie.rating.toString()

            if (movie.isFavorited) {
                btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                btnFav.setImageResource(R.drawable.ic_favorite_border)
            }
        }

        favMovie = movie
    }

    private fun attachTvShow(show: TvShow) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${show.image}")
            .into(binding.detailImg)

        binding.apply {
            detailTitle.text = show.title
            detailDate.text = show.date
            detailGenre.text = show.genre
            detailDesc.text = show.description
            detailDuration.text = resources.getString(R.string.duration_value, show.duration)
            detailSeasons.text = resources.getString(R.string.season_rv, show.seasons.toString())
            detailUserScore.text = show.rating.toString()

            if (show.isFavorited) {
                btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                btnFav.setImageResource(R.drawable.ic_favorite_border)
            }

        }

        favTvShowsEntity = show
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val ID_MOVIE = 1
        const val ID_SHOW = 2
        const val IDENTIFIER = "identifier"
        const val ID = "id"
    }
}