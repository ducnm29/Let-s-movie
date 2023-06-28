package com.letsmovie.di

import com.letsmovie.data.api.MovieApi
import com.letsmovie.data.api.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Singleton
    @Provides
    fun provideMovieApi() : MovieApi = NetworkManager.createMovieApi()
}