package com.danielefavaro.movies.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.danielefavaro.movies.MyApp
import com.danielefavaro.movies.R
import com.danielefavaro.movies.base.ktx.load
import com.danielefavaro.movies.base.ui.BaseBottomSheetDialogFragment
import com.danielefavaro.movies.base.util.Constants
import com.danielefavaro.movies.data.entities.MovieDetailModel
import com.danielefavaro.movies.databinding.MovieDetailFragmentBinding
import com.danielefavaro.movies.main.ui.model.MovieDetailFragmentEvent
import com.danielefavaro.movies.main.ui.viewmodel.MainViewModel

class MovieDetailFragment : BaseBottomSheetDialogFragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels { viewModelsFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = MovieDetailFragmentBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getLong(Constants.SHARED_OBJ)?.let {
            viewModel.getMovie(it)
        }

        observeEvent()
    }

    private fun observeEvent() {
        viewModel.event.observe(viewLifecycleOwner, {
            when (it) {
                is MovieDetailFragmentEvent.OnLoading -> binding.progressLayout.root.isVisible =
                    it.loading
                is MovieDetailFragmentEvent.OnGenericError, MovieDetailFragmentEvent.OnNetworkUnavailable -> onError()
                is MovieDetailFragmentEvent.OnMovieDetail -> setupMovie(it.movie)
            }
        })
    }

    private fun onError() {
        Toast.makeText(
            binding.root.context,
            getString(R.string.generic_error),
            Toast.LENGTH_SHORT
        ).show()
        dismiss()
    }

    private fun setupMovie(movie: MovieDetailModel) {
        binding.title.text = movie.title
        binding.description.text = movie.description
        binding.genres.text = movie.genres
        binding.rating.text = String.format("%s/%s", movie.rating, getString(R.string.rating_max))
        binding.logo.load(movie.logo)
        binding.movieDetails.visibility = View.VISIBLE
    }

    override fun setupDI() {
        (activity?.application as? MyApp)?.appComponent?.mainFactory()?.create()?.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}