package com.letsmovie.di

import com.letsmovie.data.api.MovieApi
import com.letsmovie.data.api.TvApi
import com.letsmovie.repository.MovieRepository
import com.letsmovie.repository.MovieRepositoryImpl
import com.letsmovie.repository.TvRepository
import com.letsmovie.repository.TvRepositoryImpl
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

    @Singleton
    @Provides
    fun provideTvRepository(tvApi: TvApi): TvRepository
    = TvRepositoryImpl(tvApi = tvApi)
}