package com.danielefavaro.movies

import android.app.Application
import com.danielefavaro.movies.main.di.AppComponent
import com.danielefavaro.movies.main.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}