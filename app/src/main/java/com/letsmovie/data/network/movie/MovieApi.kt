package com.letsmovie.data.network.movie

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): DataListResponse<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): DataListResponse<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): DataListResponse<MovieResponse>

    @GET("discover/movie")
    suspend fun getMovieInGenre(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: String,
        @Query("page") page: Int
    ): DataListResponse<MovieResponse>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int,
        @Query("query") query: String
    ): DataListResponse<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): DataListResponse<MovieResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendationMovie(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean
    ): DataListResponse<MovieResponse>
}