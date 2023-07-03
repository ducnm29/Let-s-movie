package com.letsmovie.data.api

import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Tv
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {
    @GET("trending/tv/day")
    suspend fun getTrendingTv(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<Tv>
    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ):DataListResponse<Tv>
}