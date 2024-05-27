package com.letsmovie.data.network.cast

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

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieRelated(
        @Path("person_id") personId: String,
        @Query("api_key") apiKey: String
    ) : MovieRelatedResponse

    @GET("person/{person_id}/images")
    suspend fun getCastImages(
        @Path("person_id") personId: String,
        @Query("api_key") apiKey: String
    ) : DataCastImageResponse
}