package com.danielefavaro.movies.main.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.danielefavaro.movies.base.exception.NetworkGenericException
import com.danielefavaro.movies.base.exception.NetworkUnavailableException
import com.danielefavaro.movies.base.util.Result
import com.danielefavaro.movies.data.database.MainDatabase
import com.danielefavaro.movies.data.service.MoviesService
import java.net.UnknownHostException
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    private val service: MoviesService,
    private val database: MainDatabase
) : MoviesDataSource {

    override suspend fun getMovie(id: Long) = try {
        val result = service.getMovie(id)
        Result.Success(result)
    } catch (e: UnknownHostException) {
        Result.Error(NetworkUnavailableException())
    } catch (e: Exception) {
        Result.Error(NetworkGenericException())
    }

    override suspend fun getMovies() = try {
        val result = service.getMovies()
        Result.Success(result)
    } catch (e: UnknownHostException) {
        Result.Error(NetworkUnavailableException())
    } catch (e: Exception) {
        Result.Error(NetworkGenericException())
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedMovies() = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        remoteMediator = MoviesRemoteMediator(
            service,
            database
        ),
        pagingSourceFactory = { database.movieDao.getMovies() }
    ).flow
}