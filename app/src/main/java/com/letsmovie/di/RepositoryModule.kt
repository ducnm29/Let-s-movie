package com.letsmovie.di

import com.letsmovie.data.api.MovieApi
import com.letsmovie.repository.MovieRepository
import com.letsmovie.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(movieApi: MovieApi): MovieRepository
    = MovieRepositoryImpl(movieApi = movieApi)
}