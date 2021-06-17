package com.danielefavaro.movies.main.di.module

import android.app.Application
import androidx.room.Room
import com.danielefavaro.movies.data.database.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(application: Application) = Room.databaseBuilder(
        application,
        MainDatabase::class.java, "main.db"
    ).build()
}
