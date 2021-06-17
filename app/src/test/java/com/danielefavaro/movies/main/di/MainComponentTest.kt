package com.danielefavaro.movies.main.di

import com.danielefavaro.movies.base.BaseUnitTest
import com.danielefavaro.movies.main.di.module.DatabaseModuleTest
import com.danielefavaro.movies.main.di.module.MainBindingModule
import com.danielefavaro.movies.main.di.module.NetworkModuleTest
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [
        DatabaseModuleTest::class,
        NetworkModuleTest::class,
        MainBindingModule::class
    ]
)
interface MainComponentTest {
    @Component.Factory
    interface Factory {
        fun create(): MainComponentTest
    }

    fun inject(base: BaseUnitTest)
}