package com.danielefavaro.movies.main.ui.viewmodel

import com.danielefavaro.movies.base.BaseUnitTest
import com.danielefavaro.movies.base.ktx.assertValues
import com.danielefavaro.movies.base.util.Result
import com.danielefavaro.movies.main.ui.model.MovieDetailFragmentEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : BaseUnitTest() {

    @Test
    fun `check getMovies response`() = runBlocking {
        when (val result = repository.getMovies()) {
            is Result.Success -> {
                assert(result.data.movieList.isNotEmpty())
            }
        }
    }

    @Test
    fun `check getMovie response`() = runBlocking {
        when (val result = repository.getMovie(1)) {
            is Result.Success -> {
                assert(result.data.movie.title.isNotBlank())
            }
        }
    }

    @Test
    fun `check getPagedMovies response`() {
        runBlocking {
            // TODO How can I trigger Paging?
            val result = repository.getPagedMovies().toList()
            assert(result.isNotEmpty())
        }
    }

    @Test
    fun `check movieDetail flow`() = runBlocking {
        val result = repository.getMovie(1) as Result.Success

        mainViewModel.event.assertValues(
            listOf(
                MovieDetailFragmentEvent.OnLoading(true),
                MovieDetailFragmentEvent.OnLoading(false),
                MovieDetailFragmentEvent.OnMovieDetail(result.data.movie)
            )
        ) {
            mainViewModel.getMovie(1)
        }
    }
}