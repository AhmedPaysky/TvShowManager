package com.osama.tvshowmanager.presentation.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.api.Error
import com.osama.tvshowmanager.data.commun.onSuccess
import com.osama.tvshowmanager.utils.hide
import com.osama.tvshowmanager.utils.show
import com.osama.tvshowmanager.utils.toast
import com.osama.tvshowmanager.data.commun.onError
import com.osama.tvshowmanager.data.commun.onLoading
import com.osama.tvshowmanager.databinding.ActivityMoviesBinding
import com.tvshowmanager.server.MoviesQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        viewModel.getListMovies()
    }

    private fun initObserver() {
        viewModel.resultListMovies.observe(this) {
            it.onSuccess { list ->
                binding.progressCircular.hide()
                setListMovies(list)
            }.onError { error ->
                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> toast(errorMessage.message) }
                    }
                }
            }.onLoading {
                binding.progressCircular.show()
            }
        }
    }

    private fun setListMovies(list: MoviesQuery.Movies) {
        with(binding.rvRickAndMorty) {
            adapter = MoviesAdapter(list)
        }
    }
}