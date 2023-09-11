package com.letsmovie.ui.movie.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.DataCastResponse
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.repository.CastRepository
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
    private val castRepository: CastRepository
) : ViewModel() {

    // Movie id for detail screen
    private var movieId: String = savedStateHandle[MovieDetailDestination.movieIdArg] ?: "811567"

    private val _movieDetail: MutableStateFlow<Result<Movie>> = MutableStateFlow(Result.Loading)
    val movieDetail: StateFlow<Result<Movie>> = _movieDetail.asStateFlow()

    private val _castList: MutableStateFlow<Result<DataCastResponse>> =
        MutableStateFlow(Result.Loading)
    val castList: StateFlow<Result<DataCastResponse>> = _castList.asStateFlow()

    private val _recommendationsMovie: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    val recommendationsMovie: StateFlow<Result<DataListResponse<Movie>>> =
        _recommendationsMovie.asStateFlow()

    init {
        getMovieDetail(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = Define.API_KEY
        )
        getCastsOfMovie(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = Define.API_KEY
        )
        getRecommendationsMovie(
            movieId = movieId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = Define.API_KEY,
            page = 1
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

    fun getCastsOfMovie(movieId: String, language: String, apiKey: String) {
        viewModelScope.launch {
            castRepository
                .getCastsOfMovie(language, apiKey, movieId)
                .collectLatest {
                    _castList.value = it
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
                .collectLatest {
                    _recommendationsMovie.value = it
                }
        }
    }
}