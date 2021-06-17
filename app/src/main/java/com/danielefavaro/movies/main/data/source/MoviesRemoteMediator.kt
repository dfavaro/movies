package com.danielefavaro.movies.main.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.danielefavaro.movies.data.database.MainDatabase
import com.danielefavaro.movies.data.entities.MovieKeyModel
import com.danielefavaro.movies.data.entities.MovieModel
import com.danielefavaro.movies.data.service.MoviesService
import retrofit2.HttpException
import java.io.IOException

private const val INITIAL_INDEX = 0
const val NETWORK_PAGE_SIZE = 50

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val service: MoviesService,
    private val database: MainDatabase
) : RemoteMediator<Int, MovieModel>() {

    override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieModel>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val movieKey = getMovieKeyClosestToCurrentPosition(state)
                movieKey?.nextKey?.minus(1) ?: INITIAL_INDEX
            }
            LoadType.PREPEND -> {
                val movieKey = getMovieKeyForFirstItem(state)
                // If the previous key is null, then the list is empty so we should wait for data
                // fetched by remote refresh and can simply skip loading this time by returning
                // `false` for endOfPaginationReached.
                val prevKey = movieKey?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val movieKey = getMovieKeyForLastItem(state)
                // If the next key is null, then the list is empty so we should wait for data
                // fetched by remote refresh and can simply skip loading this time by returning
                // `false` for endOfPaginationReached.
                val nextKey = movieKey?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                nextKey
            }
        }

        try {
            val response = service.getMovies(state.config.pageSize, page)

            val movieList = response.movieAggregate.movieList
            val endOfPaginationReached = movieList.isEmpty()
            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.movieKeysDao.deleteAll()
                    database.movieDao.deleteAll()
                }
                val prevKey = if (page == INITIAL_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = movieList.map {
                    MovieKeyModel(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                database.movieKeysDao.insertAll(keys)
                database.movieDao.insertAll(movieList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getMovieKeyForLastItem(state: PagingState<Int, MovieModel>): MovieKeyModel? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                // Get the movie keys of the last item retrieved
                database.movieKeysDao.getMovieKeyById(movie.id)
            }
    }

    private suspend fun getMovieKeyForFirstItem(state: PagingState<Int, MovieModel>): MovieKeyModel? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                // Get the movie keys of the first items retrieved
                database.movieKeysDao.getMovieKeyById(movie.id)
            }
    }

    private suspend fun getMovieKeyClosestToCurrentPosition(state: PagingState<Int, MovieModel>): MovieKeyModel? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.movieKeysDao.getMovieKeyById(id)
            }
        }
    }
}