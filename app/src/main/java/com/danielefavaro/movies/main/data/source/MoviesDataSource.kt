package com.danielefavaro.movies.main.data.source

import androidx.paging.PagingData
import com.danielefavaro.movies.base.util.Result
import com.danielefavaro.movies.data.entities.MovieByPkModel
import com.danielefavaro.movies.data.entities.MovieListModel
import com.danielefavaro.movies.data.entities.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    suspend fun getMovies(): Result<MovieListModel>
    suspend fun getMovie(id: Long): Result<MovieByPkModel>
    fun getPagedMovies(): Flow<PagingData<MovieModel>>
}