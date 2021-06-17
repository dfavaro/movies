package com.danielefavaro.movies.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.danielefavaro.movies.data.entities.MovieModel
import com.danielefavaro.movies.databinding.MovieDetailItemLayoutBinding
import com.danielefavaro.movies.main.ui.component.MoviesViewHolder

class MoviesAdapter(
    private val onClick: (item: MovieModel) -> Unit,
) : PagingDataAdapter<MovieModel, MoviesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder(
        MovieDetailItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onClick
    )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) =
                oldItem.id == newItem.id && oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) =
                oldItem == newItem
        }
    }
}