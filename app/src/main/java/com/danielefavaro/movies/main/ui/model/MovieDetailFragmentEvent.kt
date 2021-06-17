package com.danielefavaro.movies.main.ui.model

import com.danielefavaro.movies.data.entities.MovieDetailModel

sealed class MovieDetailFragmentEvent {
    data class OnLoading(val loading: Boolean) : MovieDetailFragmentEvent()
    object OnGenericError : MovieDetailFragmentEvent()
    object OnNetworkUnavailable : MovieDetailFragmentEvent()
    data class OnMovieDetail(val movie: MovieDetailModel) : MovieDetailFragmentEvent()
}