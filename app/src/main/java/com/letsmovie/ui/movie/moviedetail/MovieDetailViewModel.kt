package com.letsmovie.ui.movie.moviedetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.BuildConfig
import com.letsmovie.model.Result
import com.letsmovie.repository.CastRepository
import com.letsmovie.repository.MovieRepository
import com.letsmovie.ui.navigation.MovieDetailDestination
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
    private val castRepository: CastRepository
) : ViewModel() {

    // Movie id for detail screen
    private var movieId: String = savedStateHandle[MovieDetailDestination.movieIdArg] ?: "811567"

    private val _uiState = MutableStateFlow(MovieDetailUiState.EMPTY)
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    init {
        getMovieDetail(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = BuildConfig.API_KEY
        )
        getCastsOfMovie(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = BuildConfig.API_KEY
        )
        getRecommendationsMovie(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = BuildConfig.API_KEY,
            page = 1
        )
    }

    fun getMovieDetail(movieId: String, language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository
                .getMovieDetail(movieId = movieId, language = language, apiKey = apiKey)
                .collectLatest { result ->
                    _uiState.update { it.copy(movieState = result) }
                    when (result) {
                        is Result.Error -> {

                        }

                        Result.Loading -> {

                        }

                        is Result.Success -> {
                            _uiState.update { it.copy(movie = result.data) }
                        }
                    }
                }
        }
    }

    fun getCastsOfMovie(movieId: String, language: String, apiKey: String) {
        viewModelScope.launch {
            castRepository
                .getCastsOfMovie(language, apiKey, movieId)
                .collectLatest { result ->
                    _uiState.update { it.copy(listCastState = result) }
                    when (result) {
                        is Result.Error -> {
                            Log.d("TestDetail", result.exception)
                        }

                        Result.Loading -> {

                        }

                        is Result.Success -> {
                            _uiState.update { it.copy(listCast = result.data) }
                        }
                    }

                }
        }
    }

    fun getRecommendationsMovie(movieId: String, language: String, apiKey: String, page: Int) {
        viewModelScope.launch {
            movieRepository
                .getRecommendationMovie(
                    language = language,
                    apiKey = apiKey,
                    movieId = movieId,
                    page = page,
                    includeAdult = false
                )
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {

                        }

                        Result.Loading -> {

                        }

                        is Result.Success -> {
                            _uiState.update { it.copy(listRecommendationMovie = result.data.dataList) }
                        }
                    }
                }
        }
    }
}