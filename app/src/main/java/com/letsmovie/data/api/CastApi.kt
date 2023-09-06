package com.letsmovie.data.api

import com.letsmovie.model.DataCastResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastApi {

    @GET("movie/{movie_id}/credits")
    suspend fun getCastsOfMovie(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataCastResponse
}