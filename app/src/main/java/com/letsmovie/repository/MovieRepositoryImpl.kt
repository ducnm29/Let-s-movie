package com.letsmovie.repository

import com.letsmovie.data.api.MovieApi
import com.letsmovie.model.MovieResponse
import com.letsmovie.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getTrendingMovie(language: String, apiKey: String): Flow<MovieResponse> {
        return flow {
            emit(movieApi.getTrendingMovie(language, apiKey))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPopularMovie(language: String, apiKey: String): Flow<MovieResponse> {
        return flow {
            emit(movieApi.getPopular(language, apiKey))
        }.flowOn(Dispatchers.IO)
    }
}