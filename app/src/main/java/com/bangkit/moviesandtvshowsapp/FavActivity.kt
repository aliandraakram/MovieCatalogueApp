package com.bangkit.moviesandtvshowsapp

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.moviesandtvshowsapp.adapter.FavPagerAdapter
import com.bangkit.moviesandtvshowsapp.databinding.ActivityFavBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
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