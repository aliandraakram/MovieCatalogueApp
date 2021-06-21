package com.bangkit.moviesandtvshowsapp.di

import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowInteractor
import com.bangkit.moviesandtvshowapp.core.domain.usecase.MovieTvShowUseCase
import com.bangkit.moviesandtvshowsapp.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieTvShowUseCase> { MovieTvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewmodel(get()) }
    viewModel { TvShowsViewmodel(get()) }
    viewModel { DetailViewModel(get()) }
}

