package com.letsmovie.data.api

import com.letsmovie.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): MovieResponse
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): MovieResponse
}