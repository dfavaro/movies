package com.danielefavaro.movies.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.danielefavaro.movies.databinding.LoadStateFooterItemLayoutBinding
import com.danielefavaro.movies.main.ui.component.LoadStateViewHolder

class LoadStateAdapter(
    private val onRetryClick: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadStateViewHolder(
        LoadStateFooterItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onRetryClick
    )
}