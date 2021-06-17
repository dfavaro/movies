package com.danielefavaro.movies.data.entities

import com.google.gson.annotations.SerializedName

data class MovieAggregateModel(
    @SerializedName("movie_app_movies_aggregate")
    val movieAggregate: MovieNodeModel
)

data class MovieNodeModel(
    @SerializedName("nodes")
    val movieList: List<MovieModel>
)