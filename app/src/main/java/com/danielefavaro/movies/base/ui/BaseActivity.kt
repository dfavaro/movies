package com.danielefavaro.movies.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danielefavaro.movies.base.util.ViewModelsFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    abstract fun setupDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDI()
        super.onCreate(savedInstanceState)
    }
}