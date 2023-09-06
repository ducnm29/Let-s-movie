package com.letsmovie.repository

import com.letsmovie.data.api.CastApi
import com.letsmovie.model.DataCastResponse
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CastRepositoryImpl(
    private val castApi: CastApi
) : CastRepository {
    override suspend fun getCastsOfMovie(
        language: String,
        apiKey: String,
        movieId: String
    ): Flow<Result<DataCastResponse>> {
        return flow {
            emit(Result.Loading)
            val data = castApi.getCastsOfMovie(movieId, language, apiKey)
            emit(Result.Success(data))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }
}