package com.letsmovie.repository

import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>>

    fun getPopularMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>>

    fun getMovieDetail(
        movieId: String,
        language: String,
        apiKey: String
    ): Flow<Result<Movie>>

    fun getTopRatedMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>>

    suspend fun getTopRatedMovie(
        language: String,
        apiKey: String,
        page: Int
    ): DataListResponse<Movie>

    fun getUpComingMovie(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Movie>>>

    suspend fun getUpComingMovie(
        language: String,
        apiKey: String,
        page: Int
    ): DataListResponse<Movie>

    suspend fun getMovieInGenre(
        language: String,
        apiKey: String,
        genreId: String,
        page: Int
    ): DataListResponse<Movie>

    suspend fun getSearchMovie(
        language: String,
        apiKey: String,
        includeAdult: Boolean,
        searchKey: String,
        page: Int
    ) : DataListResponse<Movie>

    fun getNowPlayingMovie(
        language: String,
        apiKey: String,
        page: Int
    ): Flow<Result<DataListResponse<Movie>>>

    fun getRecommendationMovie(
        language: String,
        apiKey: String,
        movieId: String,
        page: Int,
        includeAdult: Boolean
    ): Flow<Result<DataListResponse<Movie>>>
}