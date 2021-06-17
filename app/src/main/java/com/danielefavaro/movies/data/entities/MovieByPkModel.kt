package com.danielefavaro.movies.data.entities

import com.google.gson.annotations.SerializedName

data class MovieByPkModel(
    @SerializedName("movie_app_movies_by_pk")
    val movie: MovieDetailModel
)

data class MovieDetailModel(
    val rating: Int,
    val title: String,
    val description: String,
    val logo: String,
    val genres: String
)