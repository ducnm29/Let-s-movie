package com.letsmovie.repository

import com.letsmovie.model.MovieResponse
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovie(language: String, apiKey: String): Flow<MovieResponse>
    fun getPopularMovie(language: String, apiKey: String): Flow<MovieResponse>
}