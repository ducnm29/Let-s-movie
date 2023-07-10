package com.letsmovie.data.api

import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Tv
import retrofit2.http.GET
import retrofit2.http.Path
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
    @GET("tv/{series_id}")
    suspend fun getTvDetail(
        @Path("series_id") tvId: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Tv
}