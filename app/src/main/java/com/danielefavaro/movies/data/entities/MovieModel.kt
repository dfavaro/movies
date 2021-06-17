package com.danielefavaro.movies.data.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val logo: String
)