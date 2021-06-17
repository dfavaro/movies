package com.danielefavaro.movies.main.di.module

import androidx.lifecycle.ViewModel
import com.danielefavaro.movies.base.util.ViewModelsFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelsFactoryModule {

    @Provides
    fun providesViewModelsFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) =
        ViewModelsFactory(creators)
}