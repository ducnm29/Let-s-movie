package com.letsmovie.repository

import com.letsmovie.data.api.cast.CastApi
import com.letsmovie.data.api.cast.toModel
import com.letsmovie.data.api.movie.toModel
import com.letsmovie.model.CastImage
import com.letsmovie.model.Credit
import com.letsmovie.model.Movie
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
    ): Flow<Result<List<Credit>>> {
        return flow {
            emit(Result.Loading)
            val data = castApi.getCastsOfMovie(movieId, language, apiKey)
            val listCredit = data.listCast.map { it.toModel() }
            emit(Result.Success(listCredit))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override suspend fun getCastDetail(
        personId: String,
        apiKey: String
    ): Flow<Result<Credit>> {
        return flow {
            emit(Result.Loading)
            val dataResponse = castApi.getCastDetail(personId = personId, apiKey = apiKey)
            emit(Result.Success(dataResponse.toModel()))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override suspend fun getMovieRelated(
        personId: String,
        apiKey: String
    ): Flow<Result<List<Movie>>> {
        return flow {
            emit(Result.Loading)
            val dataResponse = castApi.getMovieRelated(personId = personId, apiKey = apiKey)
            emit(Result.Success(dataResponse.listMovieRelated?.map { it.toModel() } ?: listOf()))
        }. catch {
            emit(Result.Error(it.toString()))
        }
    }

    override suspend fun getCastImage(
        personId: String,
        apiKey: String
    ): Flow<Result<List<CastImage>>> {
        return flow {
            emit(Result.Loading)
            val dataResponse = castApi.getCastImages(personId = personId, apiKey = apiKey)
            emit(Result.Success(dataResponse.listCastImage?.map { it.toModel() } ?: listOf()))
        }. catch {
            emit(Result.Error(it.toString()))
        }
    }
}