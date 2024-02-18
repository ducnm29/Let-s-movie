package com.letsmovie.data.api.cast

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastApi {

    @GET("movie/{movie_id}/credits")
    suspend fun getCastsOfMovie(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataCreditResponse

    @GET("person/{person_id}")
    suspend fun getCastDetail(
        @Path("person_id") personId: String,
        @Query("api_key") apiKey: String
    ): CreditResponse
}