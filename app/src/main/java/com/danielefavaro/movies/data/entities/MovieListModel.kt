package com.danielefavaro.movies.data.entities

import com.google.gson.annotations.SerializedName

data class MovieListModel(
    @SerializedName("movie_app_movies")
    val movieList: List<MovieModel>
)