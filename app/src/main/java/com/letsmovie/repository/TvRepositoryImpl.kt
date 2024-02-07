package com.letsmovie.repository

import com.letsmovie.data.api.tv.TvApi
import com.letsmovie.data.api.movie.DataListResponse
import com.letsmovie.data.api.cast.toModel
import com.letsmovie.data.api.movie.toModel
import com.letsmovie.data.api.tv.toModel
import com.letsmovie.model.Result
import com.letsmovie.model.Tv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class TvRepositoryImpl(
    private val tvApi: TvApi
) : TvRepository {
    override fun getTrendingTv(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Tv>>> {
        return flow {
            emit(Result.Loading)
            val data = tvApi.getTrendingTv(language = language, apiKey = apiKey)
            val dataParse = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(dataParse))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override fun getPopularTv(
        language: String,
        apiKey: String
    ): Flow<Result<DataListResponse<Tv>>> {
        return flow {
            emit(Result.Loading)
            val data = tvApi.getPopularTv(language = language, apiKey = apiKey)
            val dataParse = data.toModel { data.dataList.map { it.toModel() } }
            emit(Result.Success(dataParse))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }

    override fun getTvDetail(
        tvId: String,
        language: String,
        apiKey: String
    ): Flow<Result<Tv>> {
        return flow {
            emit(Result.Loading)
            val data = tvApi.getTvDetail(tvId = tvId, language = language, apiKey = apiKey)
            emit(Result.Success(data.toModel()))
        }.catch {
            emit(Result.Error(it.toString()))
        }
    }
}