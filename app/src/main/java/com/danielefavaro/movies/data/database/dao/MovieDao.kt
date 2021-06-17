package com.danielefavaro.movies.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danielefavaro.movies.data.entities.MovieModel

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<MovieModel>)

    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getMovies(): PagingSource<Int, MovieModel>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
