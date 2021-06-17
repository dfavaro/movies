package com.danielefavaro.movies.data.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "movie_keys")
data class MovieKeyModel(
    @PrimaryKey val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)