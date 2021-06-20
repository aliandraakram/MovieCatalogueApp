package com.bangkit.moviesandtvshowsapp

import android.app.Application
import com.bangkit.moviesandtvshowapp.core.di.dbModule
import com.bangkit.moviesandtvshowapp.core.di.networkModule
import com.bangkit.moviesandtvshowapp.core.di.repoModule
import com.bangkit.moviesandtvshowsapp.di.useCaseModule
import com.bangkit.moviesandtvshowsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repoModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}