package com.danielefavaro.movies.main.ui.component

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.danielefavaro.movies.databinding.LoadStateFooterItemLayoutBinding

class LoadStateViewHolder(
    private val binding: LoadStateFooterItemLayoutBinding,
    private val onRetryClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.retryButton.setOnClickListener {
            onRetryClick.invoke()
        }
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }
}