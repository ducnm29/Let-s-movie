package com.letsmovie.repository

import com.letsmovie.model.DataCastResponse
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface CastRepository {
    suspend fun getCastsOfMovie(
        language: String,
        apiKey: String,
        movieId: String
    ) : Flow<Result<DataCastResponse>>
}