package com.letsmovie.di

import com.letsmovie.data.network.cast.CastApi
import com.letsmovie.data.network.genre.GenreApi
import com.letsmovie.data.network.movie.MovieApi
import com.letsmovie.data.network.tv.TvApi
import com.letsmovie.repository.CastRepository
import com.letsmovie.repository.CastRepositoryImpl
import com.letsmovie.repository.GenreRepository
import com.letsmovie.repository.GenreRepositoryImpl
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

    @Singleton
    @Provides
    fun provideGenreRepository(genreApi: GenreApi): GenreRepository
    = GenreRepositoryImpl(genreApi = genreApi)

    @Singleton
    @Provides
    fun provideCastRepository(castApi: CastApi): CastRepository
    = CastRepositoryImpl(castApi = castApi)
}