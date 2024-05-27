package com.letsmovie.di

import com.letsmovie.data.network.cast.CastApi
import com.letsmovie.data.network.genre.GenreApi
import com.letsmovie.data.network.movie.MovieApi
import com.letsmovie.data.network.NetworkManager
import com.letsmovie.data.network.tv.TvApi
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
    fun provideMovieApi(): MovieApi = NetworkManager.createMovieApi()

    @Singleton
    @Provides
    fun provideTvApi(): TvApi = NetworkManager.createTvApi()

    @Singleton
    @Provides
    fun provideGenreApi(): GenreApi = NetworkManager.createGenreApi()

    @Singleton
    @Provides
    fun provideCastApi(): CastApi = NetworkManager.createCastApi()
}