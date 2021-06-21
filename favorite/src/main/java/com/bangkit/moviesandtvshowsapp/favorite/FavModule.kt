package com.bangkit.moviesandtvshowsapp.favorite

import com.bangkit.moviesandtvshowsapp.favorite.viewmodel.FavMovieViewModel
import com.bangkit.moviesandtvshowsapp.favorite.viewmodel.FavTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favMovieModule = module {
    viewModel { FavMovieViewModel(get()) }
}

val favTvShowModule = module {
    viewModel { FavTvShowViewModel(get()) }
}