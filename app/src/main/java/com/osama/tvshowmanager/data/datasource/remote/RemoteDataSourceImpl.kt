package com.osama.tvshowmanager.data.datasource.remote

import android.content.Context
import com.apollographql.apollo.coroutines.await
import com.osama.tvshowmanager.data.GraphQlApolloClient
import com.osama.tvshowmanager.R
import com.osama.tvshowmanager.data.commun.DataSourceException
import com.osama.tvshowmanager.data.commun.MoviesResult
import com.tvshowmanager.server.CreateMovieMutation
import com.tvshowmanager.server.MoviesQuery
import com.tvshowmanager.server.type.CreateMovieInput

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getMovies(): MoviesResult<MoviesQuery.Movies?> {
        return try {
            val result = GraphQlApolloClient.getMovies().await()
            if (result.hasErrors()) {
                MoviesResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                MoviesResult.Success(result.data?.movies)
            }
        } catch (e: Exception) {
            MoviesResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

    override suspend fun createMovie(input: CreateMovieInput): MoviesResult<CreateMovieMutation.Data?> {
        return try {
            val result = GraphQlApolloClient.createMovie(input)?.await()
            if (result?.hasErrors() == true) {
                MoviesResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                MoviesResult.Success(result?.data)
            }
        } catch (e: Exception) {
            MoviesResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }
}