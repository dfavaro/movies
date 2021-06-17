package com.danielefavaro.movies.main.ui.component

import androidx.recyclerview.widget.RecyclerView
import com.danielefavaro.movies.base.ktx.load
import com.danielefavaro.movies.data.entities.MovieModel
import com.danielefavaro.movies.databinding.MovieDetailItemLayoutBinding

class MoviesViewHolder(
    private val binding: MovieDetailItemLayoutBinding,
    private val onClick: (item: MovieModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieModel) {
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
        binding.title.text = item.title
        binding.description.text = item.description
        binding.logo.load(item.logo)
    }
}