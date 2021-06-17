package com.danielefavaro.movies.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.danielefavaro.movies.base.util.Result
import com.danielefavaro.movies.main.domain.MoviesRepository
import com.danielefavaro.movies.main.ui.model.MovieDetailFragmentEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _event = MutableLiveData<MovieDetailFragmentEvent>()
    val event: LiveData<MovieDetailFragmentEvent> = _event

    // TODO Create UI model and map received items into it
    fun getPagedMovies() = repository.getPagedMovies()
        .cachedIn(viewModelScope)

    fun getMovie(id: Long) {
        viewModelScope.launch {
            _event.value = MovieDetailFragmentEvent.OnLoading(true)
            when (val result = repository.getMovie(id)) {
                is Result.Success -> {
                    _event.value = MovieDetailFragmentEvent.OnLoading(false)
                    _event.value = MovieDetailFragmentEvent.OnMovieDetail(result.data.movie)
                }
                is Result.Error -> {
                    _event.value = MovieDetailFragmentEvent.OnLoading(false)
                    _event.value = MovieDetailFragmentEvent.OnGenericError
                }
            }
        }
    }
}