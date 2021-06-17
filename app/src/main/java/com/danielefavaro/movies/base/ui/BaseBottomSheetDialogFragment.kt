package com.danielefavaro.movies.base.ui

import android.content.Context
import com.danielefavaro.movies.base.util.ViewModelsFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    abstract fun setupDI()

    override fun onAttach(context: Context) {
        setupDI()
        super.onAttach(context)
    }
}