package com.letsmovie.repository

import com.letsmovie.data.api.TvApi
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Tv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvRepositoryImpl(
    private val tvApi: TvApi
): TvRepository {
    override fun getTrendingTv(language: String, apiKey: String): Flow<DataListResponse<Tv>> {
        return flow {
            emit(tvApi.getTrendingTv(language = language, apiKey = apiKey))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPopularTv(language: String, apiKey: String): Flow<DataListResponse<Tv>> {
        return flow{
            emit(tvApi.getPopularTv(language = language, apiKey = apiKey))
        }.flowOn(Dispatchers.IO)
    }

    override fun getTvDetail(tvId: String, language: String, apiKey: String): Flow<Tv> {
        return flow {
            emit(tvApi.getTvDetail(tvId = tvId, language = language, apiKey = apiKey))
        }.flowOn(Dispatchers.IO)
    }
}