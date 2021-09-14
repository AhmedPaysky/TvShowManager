package com.osama.tvshowmanager.presentation.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osama.tvshowmanager.data.commun.MoviesResult
import com.osama.tvshowmanager.domain.usecases.MoviesUseCase
import com.tvshowmanager.server.CreateMovieMutation
import com.tvshowmanager.server.MoviesQuery
import com.tvshowmanager.server.type.CreateMovieInput
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _resultListMovies = MutableLiveData<MoviesResult<MoviesQuery.Movies>>()
    val resultListMovies: LiveData<MoviesResult<MoviesQuery.Movies>> = _resultListMovies

    fun getListMovies() {
        viewModelScope.launch {
            moviesUseCase().collect {
                _resultListMovies.postValue(it)
            }
        }
    }

    private val _resultCreateMovie = MutableLiveData<MoviesResult<CreateMovieMutation.Data>>()
    val resultCreateMovie: LiveData<MoviesResult<CreateMovieMutation.Data>> = _resultCreateMovie

    fun createMovie(input: CreateMovieInput) {
        viewModelScope.launch {
            moviesUseCase(input).collect {
                _resultCreateMovie.postValue(it)
            }
        }
    }
}