package com.osama.tvshowmanager.data

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloMutationCall
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Input
import com.osama.tvshowmanager.BaseApp
import com.osama.tvshowmanager.data.commun.BASE_URL
import com.osama.tvshowmanager.domain.models.SingleMovieModel
import com.tvshowmanager.server.CreateMovieMutation
import com.tvshowmanager.server.MoviesQuery
import com.tvshowmanager.server.type.CreateMovieFieldsInput
import com.tvshowmanager.server.type.CreateMovieInput
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object GraphQlApolloClient {

    private fun apolloClient(context: Context = BaseApp.appContext): ApolloClient =
        ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor(context))
                .build()
        ).build()

    fun getMovies(): ApolloQueryCall<MoviesQuery.Data> =
        apolloClient().query(MoviesQuery())

    fun createMovie(input: CreateMovieInput): ApolloMutationCall<CreateMovieMutation.Data>? =
        apolloClient().mutate(CreateMovieMutation(input))

}


private class AuthorizationInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "X-Parse-Application-Id",
                "AaQjHwTIQtkCOhtjJaN/nDtMdiftbzMWW5N8uRZ+DNX9LI8AOziS10eHuryBEcCI"
            )
            .addHeader(
                "X-Parse-Master-Key",
                "yiCk1DW6WHWG58wjj3C4pB/WyhpokCeDeSQEXA5HaicgGh4pTUd+3/rMOR5xu1Yi"
            )
            .build()

        return chain.proceed(request)
    }
}
