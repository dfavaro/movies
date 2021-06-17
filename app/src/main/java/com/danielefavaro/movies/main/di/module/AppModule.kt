package com.danielefavaro.movies.main.di.module

import com.danielefavaro.movies.main.di.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class AppModule