package com.danielefavaro.movies.data.service

import com.danielefavaro.movies.data.entities.MovieAggregateModel
import com.danielefavaro.movies.data.entities.MovieByPkModel
import com.danielefavaro.movies.data.entities.MovieListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {

    @GET("movie-app/movies")
    suspend fun getMovies(): MovieListModel

    @GET("movie-app/movies/limit/{limit}/offset/{fromIndex}")
    suspend fun getMovies(
        @Path("limit") limit: Int,
        @Path("fromIndex") fromIndex: Int
    ): MovieAggregateModel

    @GET("movie-app/movies/{id}")
    suspend fun getMovie(@Path("id") id: Long): MovieByPkModel
}