package com.bangkit.moviesandtvshowsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.moviesandtvshowsapp.adapter.SectionsPagerAdapter
import com.bangkit.moviesandtvshowsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = SectionsPagerAdapter(this)
        TabLayoutMediator(binding.tab, binding.viewpager) { tab, position ->
            tab.text = resources.getString(TITLE[position])
        }.attach()

        title = resources.getString(R.string.home_title)
    }

    companion object {
        @StringRes
        private val TITLE = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_fav) {
            startActivity(Intent(this, FavActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}