package com.letsmovie.ui.movie.moviedetail

import com.letsmovie.model.Credit
import com.letsmovie.model.Movie
import com.letsmovie.model.Result

data class MovieDetailUiState(
    val movie: Movie = Movie.EMPTY,
    val listCast: List<Credit> = listOf(),
    val listRecommendationMovie: List<Movie> = listOf(),
    val movieState: Result<Movie> = Result.Loading,
    val listCastState: Result<List<Credit>> = Result.Loading
){
    val isLoading by lazy {
        movieState is Result.Loading ||
        listCastState is Result.Loading
    }

    val isError by lazy {
        movieState is Result.Error ||
        listCastState is Result.Error
    }
    companion object{
        val EMPTY = MovieDetailUiState()
    }
}
