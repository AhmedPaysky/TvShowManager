package com.osama.tvshowmanager.data.repository

import android.content.Context
import com.apollographql.apollo.api.Response
import com.osama.tvshowmanager.data.commun.MoviesResult
import com.osama.tvshowmanager.data.datasource.remote.RemoteDataSource
import com.osama.tvshowmanager.domain.models.SingleMovieModel
import com.osama.tvshowmanager.domain.repository.AppRepository
import com.tvshowmanager.server.CreateMovieMutation
import com.tvshowmanager.server.MoviesQuery
import com.tvshowmanager.server.type.CreateMovieInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) :
    AppRepository {

    override suspend fun getMovies(): Flow<MoviesResult<MoviesQuery.Movies>> =
        flow {
            when (val result = remoteDataSource.getMovies()) {
                is MoviesResult.Success -> {
                    result.data?.let {
                        it.apply {
                            emit(MoviesResult.Success(this))
                        }
                    }
                }
                is MoviesResult.Error -> {
                    emit(MoviesResult.Error(result.exception))
                }
            }
        }.onStart { emit(MoviesResult.Loading) }

    override suspend fun createMovie(input: CreateMovieInput): Flow<MoviesResult<CreateMovieMutation.Data>> =
        flow {
            when (val result = remoteDataSource.createMovie(input)) {
                is MoviesResult.Success -> {
                    result.data?.let {
                        it.apply {
                            emit(MoviesResult.Success(this))
                        }
                    }
                }
                is MoviesResult.Error -> {
                    emit(MoviesResult.Error(result.exception))
                }
            }
        }.onStart { emit(MoviesResult.Loading) }


}