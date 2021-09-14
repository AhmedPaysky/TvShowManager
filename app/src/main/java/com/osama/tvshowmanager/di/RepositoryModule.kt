package com.osama.tvshowmanager.di

import android.content.Context
import com.osama.tvshowmanager.data.datasource.remote.RemoteDataSourceImpl
import com.osama.tvshowmanager.data.repository.AppRepositoryImpl
import com.osama.tvshowmanager.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(): AppRepository {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        return AppRepositoryImpl(remoteDataSourceImpl)
    }
}