package com.letsmovie.repository

import com.letsmovie.data.api.MovieApi
import com.letsmovie.model.Movie
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getTrendingMovie(language: String, apiKey: String): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getTrendingMovie(language, apiKey)
            emit(Result.Success(data))
        }.catch { throwable -> emit(Result.Error(throwable.toString())) }
    }

    override fun getPopularMovie(language: String, apiKey: String): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getPopularMovie(language, apiKey)
            emit(Result.Success(data))
        }.catch { throwable ->
            emit(Result.Error(throwable.toString()))
        }

    }

    override fun getMovieDetail(movieId: String, language: String, apiKey: String): Flow<Movie> {
        return flow {

            emit(movieApi.getMovieDetail(movieId, language, apiKey))
        }
    }

    override fun getTopRatedMovie(language: String, apiKey: String): Flow<DataListResponse<Movie>> {
        return flow {
            emit(movieApi.getTopRatedMovie(language, apiKey))
        }
    }
}