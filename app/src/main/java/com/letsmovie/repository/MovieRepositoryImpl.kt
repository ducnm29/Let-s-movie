package com.letsmovie.repository

import com.letsmovie.data.api.movie.MovieApi
import com.letsmovie.data.api.cast.toModel
import com.letsmovie.data.api.movie.DataListResponse
import com.letsmovie.data.api.movie.toModel
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override fun getTrendingMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getTrendingMovie(language, apiKey)
            val parseData = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(parseData))
        }.catch { throwable -> emit(Result.Error(throwable.toString())) }
    }

    override fun getPopularMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getPopularMovie(language, apiKey)
            val parseData = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(parseData))
        }.catch { throwable ->
            emit(Result.Error(throwable.toString()))
        }

    }

    override fun getMovieDetail(
        movieId: String,
        language: String,
        apiKey: String
    ): Flow<Result<Movie>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getMovieDetail(movieId, language, apiKey)
            emit(Result.Success(data.toModel()))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override fun getTopRatedMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getTopRatedMovie(language, apiKey)
            val parseData = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(parseData))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override suspend fun getTopRatedMovie(
        language: String,
        apiKey: String,
        page: Int
    ): DataListResponse<Movie> {
        val data = movieApi.getTopRatedMovie(language, apiKey, page)
        return data.toModel { data.dataList.map { it.toModel() } }
    }

    override fun getUpComingMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getUpComingMovie(language, apiKey)
            val parseData = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(parseData))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override suspend fun getUpComingMovie(
        language: String,
        apiKey: String,
        page: Int
    ): DataListResponse<Movie> {
        val data = movieApi.getUpComingMovie(language, apiKey, page)
        return data.toModel { data.dataList.map { it.toModel() } }
    }

    override suspend fun getMovieInGenre(
        language: String,
        apiKey: String,
        genreId: String,
        page: Int
    ): DataListResponse<Movie> {
        val data = movieApi.getMovieInGenre(language, apiKey, genreId, page)
        return data.toModel { data.dataList.map { it.toModel() } }
    }

    override suspend fun getSearchMovie(
        language: String,
        apiKey: String,
        includeAdult: Boolean,
        searchKey: String,
        page: Int
    ): DataListResponse<Movie> {
        val data = movieApi.getSearchMovie(language, apiKey, includeAdult, page, searchKey)
        return data.toModel { data.dataList.map { it.toModel() } }
    }

    override fun getNowPlayingMovie(
        language: String,
        apiKey: String,
        page: Int
    ): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getNowPlayingMovie(language, apiKey, page)
            val parseData = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(parseData))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override fun getRecommendationMovie(
        language: String,
        apiKey: String,
        movieId: String,
        page: Int,
        includeAdult: Boolean
    ): Flow<Result<DataListResponse<Movie>>> {
        return flow {
            emit(Result.Loading)
            val data = movieApi.getRecommendationMovie(movieId, language, apiKey, page, includeAdult)
            val parseData = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(parseData))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }
}