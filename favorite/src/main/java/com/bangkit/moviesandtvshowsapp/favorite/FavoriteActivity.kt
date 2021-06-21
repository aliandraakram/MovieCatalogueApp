package com.bangkit.moviesandtvshowsapp.favorite

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.moviesandtvshowsapp.R
import com.bangkit.moviesandtvshowsapp.favorite.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = FavPagerAdapter(this)
        TabLayoutMediator(binding.tab, binding.viewpager) { tab, position ->
            tab.text = resources.getString(TITLE[position])
        }.attach()

        title = resources.getString(R.string.favorite)
    }

    companion object {
        @StringRes
        private val TITLE = intArrayOf(R.string.movies, R.string.tv_shows)
    }
}