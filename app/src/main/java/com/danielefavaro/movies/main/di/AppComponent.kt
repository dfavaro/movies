package com.danielefavaro.movies.main.di

import android.app.Application
import com.danielefavaro.movies.main.di.module.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        MoviesNetworkModule::class,
        ViewModelsFactoryModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(application: Application)
    fun mainFactory(): MainComponent.Factory?
}