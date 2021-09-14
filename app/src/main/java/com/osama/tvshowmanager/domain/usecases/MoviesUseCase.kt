package com.osama.tvshowmanager.domain.usecases

import com.osama.tvshowmanager.domain.repository.AppRepository
import com.tvshowmanager.server.type.CreateMovieInput
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke() = appRepository.getMovies()
    suspend operator fun invoke(input: CreateMovieInput) = appRepository.createMovie(input)
}