package com.osama.tvshowmanager.domain.repository

import android.content.Context
import com.apollographql.apollo.api.Response
import com.osama.tvshowmanager.domain.models.SingleMovieModel
import com.osama.tvshowmanager.data.commun.MoviesResult
import com.tvshowmanager.server.CreateMovieMutation
import com.tvshowmanager.server.MoviesQuery
import com.tvshowmanager.server.type.CreateMovieInput
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getMovies(): Flow<MoviesResult<MoviesQuery.Movies>>
    suspend fun createMovie(input: CreateMovieInput): Flow<MoviesResult<CreateMovieMutation.Data>>
}