package com.danielefavaro.movies.main.di

import com.danielefavaro.movies.main.di.module.MainBindingModule
import com.danielefavaro.movies.main.di.scope.MainScope
import com.danielefavaro.movies.main.ui.MainActivity
import com.danielefavaro.movies.main.ui.MainFragment
import com.danielefavaro.movies.main.ui.MovieDetailFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = [
        MainBindingModule::class
    ]
)
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)
}