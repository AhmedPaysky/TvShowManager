package com.osama.tvshowmanager.data.datasource.remote

import android.content.Context
import com.osama.tvshowmanager.data.commun.MoviesResult
import com.tvshowmanager.server.CreateMovieMutation
import com.tvshowmanager.server.MoviesQuery
import com.tvshowmanager.server.type.CreateMovieFieldsInput
import com.tvshowmanager.server.type.CreateMovieInput

interface RemoteDataSource {
    suspend fun getMovies(): MoviesResult<MoviesQuery.Movies?>
    suspend fun createMovie(input: CreateMovieInput): MoviesResult<CreateMovieMutation.Data?>
}