package com.danielefavaro.movies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danielefavaro.movies.data.entities.MovieKeyModel

@Dao
interface MovieKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<MovieKeyModel>)

    @Query("SELECT * FROM movie_keys WHERE id = :id")
    suspend fun getMovieKeyById(id: Long): MovieKeyModel?

    @Query("DELETE FROM movie_keys")
    suspend fun deleteAll()
}
