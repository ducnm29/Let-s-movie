package com.letsmovie.ui.movie

import com.letsmovie.data.network.movie.DataListResponse
import com.letsmovie.model.Genre
import com.letsmovie.model.Movie
import com.letsmovie.model.Result

data class MovieUiState(
    val trendingMovieState:  Result<DataListResponse<Movie>> = Result.Loading,
    val popularMovieState:  Result<DataListResponse<Movie>> = Result.Loading,
    val topRatedMovieState:  Result<DataListResponse<Movie>> = Result.Loading,
    val upComingMovieState:  Result<DataListResponse<Movie>> = Result.Loading,
    val nowPlayingMovieState:  Result<DataListResponse<Movie>> = Result.Loading,
    val listTrendingMovie: List<Movie> = listOf(),
    val listPopularMovie: List<Movie> = listOf(),
    val listTopRatedMovie: List<Movie> = listOf(),
    val listUpComingMovie: List<Movie> = listOf(),
    val listNowPlayingMovie: List<Movie> = listOf(),
    val listGenre: List<Genre> = listOf()
){
    val isLoading by lazy {
        trendingMovieState is Result.Loading ||
        popularMovieState is Result.Loading ||
        topRatedMovieState is Result.Loading ||
        upComingMovieState is Result.Loading ||
        nowPlayingMovieState is Result.Loading
    }
    companion object{
        val EMPTY = MovieUiState()
    }
}
