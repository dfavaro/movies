package com.danielefavaro.movies.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielefavaro.movies.MyApp
import com.danielefavaro.movies.R
import com.danielefavaro.movies.base.ui.BaseFragment
import com.danielefavaro.movies.base.util.Constants
import com.danielefavaro.movies.databinding.MainFragmentBinding
import com.danielefavaro.movies.main.ui.adapter.LoadStateAdapter
import com.danielefavaro.movies.main.ui.adapter.MoviesAdapter
import com.danielefavaro.movies.main.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : BaseFragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val moviesAdapter = MoviesAdapter(onClick = {
        findNavController().navigate(
            R.id.action_mainFragment_to_movieDetailFragment, bundleOf(
                Constants.SHARED_OBJ to it.id
            )
        )
    })

    private val viewModel: MainViewModel by activityViewModels { viewModelsFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = MainFragmentBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeEvent()
    }

    private fun initViews() {
        // recyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = moviesAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { moviesAdapter.retry() }
            )
        }

        // recyclerView adapter
        moviesAdapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds
            binding.recyclerView.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh
            binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails
            binding.retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error
            // Show error
            (loadState.append as? LoadState.Error)?.let {
                Snackbar.make(binding.root, R.string.generic_error, Snackbar.LENGTH_SHORT).show()
            }
        }

        // retry button
        binding.retryButton.setOnClickListener {
            moviesAdapter.retry()
        }
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            viewModel.getPagedMovies().collectLatest {
                moviesAdapter.submitData(it)
            }
        }
    }

    override fun setupDI() {
        (activity?.application as? MyApp)?.appComponent?.mainFactory()?.create()?.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}