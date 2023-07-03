package com.letsmovie.repository

import com.letsmovie.model.Movie
import com.letsmovie.model.DataListResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovie(language: String, apiKey: String): Flow<DataListResponse<Movie>>
    fun getPopularMovie(language: String, apiKey: String): Flow<DataListResponse<Movie>>
}