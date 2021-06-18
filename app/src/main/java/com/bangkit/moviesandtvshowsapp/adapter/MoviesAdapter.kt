package com.bangkit.moviesandtvshowsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.moviesandtvshowsapp.DetailActivity
import com.bangkit.moviesandtvshowsapp.databinding.ItemMoviesBinding
import com.bangkit.moviesandtvshowsapp.dataclass.entity.MoviesEntity
import com.bangkit.moviesandtvshowsapp.domain.model.Movie
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val data = mutableListOf<Movie>()

    fun setMovie(input: List<Movie>){
        data.clear()
        data.addAll(input)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = data[position]

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w200${movie.image}")
            .into(holder.binding.imgMovies)

        holder.binding.apply {
            titleMovies.text = movie.title
            dateMovies.text = movie.date
            descMovies.text = movie.description
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.ID, movie.movId)
                putExtra(DetailActivity.IDENTIFIER, 1)
            }
            it.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int = data.size

}