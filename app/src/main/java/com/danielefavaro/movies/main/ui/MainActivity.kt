package com.danielefavaro.movies.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.danielefavaro.movies.MyApp
import com.danielefavaro.movies.base.ui.BaseActivity
import com.danielefavaro.movies.databinding.MainActivityBinding
import com.danielefavaro.movies.main.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding

    private val viewModel: MainViewModel by viewModels { viewModelsFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)
        }
    }

    override fun setupDI() {
        (application as MyApp).appComponent.mainFactory()?.create()?.inject(this)
    }
}