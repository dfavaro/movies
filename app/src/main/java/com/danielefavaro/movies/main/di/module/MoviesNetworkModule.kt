package com.danielefavaro.movies.main.di.module

import com.danielefavaro.movies.data.service.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MoviesNetworkModule {

    @Singleton
    @Provides
    fun providesMoviesService(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)
}