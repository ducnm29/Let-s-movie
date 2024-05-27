package com.letsmovie.data.network.genre

import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {
    @GET("genre/movie/list")
    suspend fun getMovieGenre(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataGenreResponse

    @GET("genre/tv/list")
    suspend fun getTvGenre(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataGenreResponse
}