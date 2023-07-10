package com.letsmovie.repository

import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Tv
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTrendingTv(language: String, apiKey: String): Flow<DataListResponse<Tv>>
    fun getPopularTv(language: String, apiKey: String): Flow<DataListResponse<Tv>>
    fun getTvDetail(tvId: String, language: String, apiKey: String): Flow<Tv>
}