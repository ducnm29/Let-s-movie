package com.letsmovie.repository

import com.letsmovie.data.api.MovieApi
import com.letsmovie.model.Movie
import com.letsmovie.model.DataListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getTrendingMovie(language: String, apiKey: String): Flow<DataListResponse<Movie>> {
        return flow {
            emit(movieApi.getTrendingMovie(language, apiKey))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPopularMovie(language: String, apiKey: String): Flow<DataListResponse<Movie>> {
        return flow {
            emit(movieApi.getPopularMovie(language, apiKey))
        }.flowOn(Dispatchers.IO)
    }
}