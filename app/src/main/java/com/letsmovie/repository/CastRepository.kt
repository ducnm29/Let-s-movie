package com.letsmovie.repository

import com.letsmovie.model.CastImage
import com.letsmovie.model.Credit
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface CastRepository {
    suspend fun getCastsOfMovie(
        language: String,
        apiKey: String,
        movieId: String
    ): Flow<Result<List<Credit>>>

    suspend fun getCastDetail(
        personId: String,
        apiKey: String,
    ): Flow<Result<Credit>>

    suspend fun getMovieRelated(
        personId: String,
        apiKey: String,
    ): Flow<Result<List<Movie>>>

    suspend fun getCastImage(
        personId: String,
        apiKey: String
    ): Flow<Result<List<CastImage>>>
}