package com.danielefavaro.movies.main.domain

import androidx.paging.PagingData
import com.danielefavaro.movies.base.util.Result
import com.danielefavaro.movies.data.entities.MovieByPkModel
import com.danielefavaro.movies.data.entities.MovieListModel
import com.danielefavaro.movies.data.entities.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Result<MovieListModel>
    fun getPagedMovies(): Flow<PagingData<MovieModel>>
    suspend fun getMovie(id: Long): Result<MovieByPkModel>
}