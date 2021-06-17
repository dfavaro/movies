package com.danielefavaro.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielefavaro.movies.data.database.dao.MovieDao
import com.danielefavaro.movies.data.database.dao.MovieKeysDao
import com.danielefavaro.movies.data.entities.MovieKeyModel
import com.danielefavaro.movies.data.entities.MovieModel

@Database(
    entities = [
        MovieModel::class,
        MovieKeyModel::class
    ],
    version = MainDatabase.VERSION,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val movieKeysDao: MovieKeysDao

    companion object {
        const val VERSION = 1
    }
}