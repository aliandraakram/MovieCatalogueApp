package com.bangkit.moviesandtvshowsapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkit.moviesandtvshowsapp.fragment.MovieFragment
import com.bangkit.moviesandtvshowsapp.fragment.TvShowsFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowsFragment()
        }

        return fragment as Fragment
    }
}