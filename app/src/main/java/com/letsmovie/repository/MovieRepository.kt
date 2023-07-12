package com.letsmovie.repository

import com.letsmovie.model.Movie
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovie(language: String, apiKey: String): Flow<Result<DataListResponse<Movie>>>
    fun getPopularMovie(language: String, apiKey: String): Flow<Result<DataListResponse<Movie>>>
    fun getMovieDetail(movieId: String, language: String, apiKey: String): Flow<Movie>
    fun getTopRatedMovie(language: String, apiKey: String): Flow<DataListResponse<Movie>>
}