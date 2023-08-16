package com.letsmovie.ui.movie.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.repository.MovieRepository
import com.letsmovie.ui.navigation.MovieDetailDestination
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    // Movie id for detail screen
    private var movieId: String = savedStateHandle[MovieDetailDestination.movieIdArg] ?: "811567"

    private val _movieDetail: MutableStateFlow<Result<Movie>> = MutableStateFlow(Result.Loading)
    val movieDetail: StateFlow<Result<Movie>> = _movieDetail.asStateFlow()

    init {
        getMovieDetail(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = Define.API_KEY
        )
    }

    fun getMovieDetail(movieId: String, language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository
                .getMovieDetail(movieId = movieId, language = language, apiKey = apiKey)
                .collectLatest {
                    _movieDetail.value = it
                }
        }
    }
}