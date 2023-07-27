package com.letsmovie.data.api

import com.letsmovie.model.Movie
import com.letsmovie.model.DataListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<Movie>
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<Movie>
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Movie
    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<Movie>
    @GET("movie/upcoming")
    suspend fun getUpComingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<Movie>
}