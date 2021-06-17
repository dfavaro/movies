package com.danielefavaro.movies.main.di.module

import android.content.Context
import androidx.room.Room
import com.danielefavaro.movies.data.database.MainDatabase
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
class DatabaseModuleTest {

    @Singleton
    @Provides
    fun providesDatabase(): MainDatabase {
        val context: Context = mockk()
        return Room.inMemoryDatabaseBuilder(
            context, MainDatabase::class.java
        ).build()
    }

}