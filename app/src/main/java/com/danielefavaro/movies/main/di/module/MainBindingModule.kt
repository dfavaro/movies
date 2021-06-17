package com.danielefavaro.movies.main.di.module

import androidx.lifecycle.ViewModel
import com.danielefavaro.movies.main.data.MoviesRepositoryImpl
import com.danielefavaro.movies.main.data.source.MoviesDataSource
import com.danielefavaro.movies.main.data.source.MoviesDataSourceImpl
import com.danielefavaro.movies.main.di.scope.ViewModelKey
import com.danielefavaro.movies.main.domain.MoviesRepository
import com.danielefavaro.movies.main.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsMainRepository(repository: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun bindsMainDataSource(sourceImpl: MoviesDataSourceImpl): MoviesDataSource
}