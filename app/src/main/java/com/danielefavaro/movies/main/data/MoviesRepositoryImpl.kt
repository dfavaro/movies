package com.danielefavaro.movies.main.data

import androidx.paging.PagingData
import com.danielefavaro.movies.data.entities.MovieModel
import com.danielefavaro.movies.main.data.source.MoviesDataSource
import com.danielefavaro.movies.main.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val source: MoviesDataSource
) : MoviesRepository {

    override suspend fun getMovie(id: Long) = source.getMovie(id)

    override suspend fun getMovies() = source.getMovies()

    override fun getPagedMovies(): Flow<PagingData<MovieModel>> = source.getPagedMovies()
}