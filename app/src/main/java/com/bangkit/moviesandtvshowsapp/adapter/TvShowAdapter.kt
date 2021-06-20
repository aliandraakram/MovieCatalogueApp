package com.bangkit.moviesandtvshowsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.moviesandtvshowapp.core.databinding.ItemTvShowBinding
import com.bangkit.moviesandtvshowapp.core.domain.model.TvShow
import com.bangkit.moviesandtvshowsapp.DetailActivity
import com.bumptech.glide.Glide

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val data = mutableListOf<TvShow>()

    fun setShow(input: List<TvShow>) {
        data.clear()
        data.addAll(input)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTvShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = data[position]

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w200${tvShow.image}")
            .into(holder.binding.imgTvshows)

        holder.binding.apply {
            titleTvshows.text = tvShow.title
            dateTvshows.text = tvShow.date
            descTvShows.text = tvShow.description
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.ID, tvShow.tvId)
                putExtra(DetailActivity.IDENTIFIER, 2)
            }

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}