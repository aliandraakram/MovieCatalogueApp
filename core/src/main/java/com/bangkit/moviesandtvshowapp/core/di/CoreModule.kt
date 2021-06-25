package com.bangkit.moviesandtvshowapp.core.di

import androidx.room.Room
import com.bangkit.moviesandtvshowapp.core.data.source.MovieTvShowRepository
import com.bangkit.moviesandtvshowapp.core.data.source.local.LocalDataSource
import com.bangkit.moviesandtvshowapp.core.data.source.local.database.MovieTvShowDatabase
import com.bangkit.moviesandtvshowapp.core.data.source.remote.RemoteDataSource
import com.bangkit.moviesandtvshowapp.core.data.source.remote.response.api.ApiService
import com.bangkit.moviesandtvshowapp.core.domain.repository.MovieTvShowDataSource
import com.bangkit.moviesandtvshowapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dbModule = module {
    factory { get<MovieTvShowDatabase>().movieTvShowDao() }
    single {
        val pass: ByteArray = SQLiteDatabase.getBytes("movietvshow".toCharArray())
        val factory = SupportFactory(pass)
        Room.databaseBuilder(
            androidContext(),
            MovieTvShowDatabase::class.java,
            "MovieTvShow.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certifPinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certifPinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repoModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MovieTvShowDataSource> { MovieTvShowRepository(get(), get(), get()) }
}
