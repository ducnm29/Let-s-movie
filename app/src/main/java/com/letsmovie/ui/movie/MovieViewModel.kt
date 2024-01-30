package com.letsmovie.ui.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.BuildConfig
import com.letsmovie.model.DataGenreResponse
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.repository.GenreRepository
import com.letsmovie.repository.MovieRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState.EMPTY)
    val uiState: StateFlow<MovieUiState> = _uiState


    private val _trendingMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    val trendingMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _trendingMovieStateFlow.asStateFlow()

    // Pull to refresh
    private val _refreshing = mutableStateOf(false)
    var refreshing: State<Boolean> = _refreshing

    init {
        refreshData()
    }

    fun getTrendingMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getTrendingMovie(language, apiKey)
                .collectLatest { trendingMovieResult ->
                    _uiState.update { it.copy(trendingMovieState = trendingMovieResult) }
                    when (trendingMovieResult) {
                        Result.Loading -> {

                        }

                        is Result.Error -> {
                            // Handle error
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listTrendingMovie = trendingMovieResult.data.dataList)
                            }
                        }
                    }

                }
        }
    }

    fun getPopularMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getPopularMovie(language, apiKey).collectLatest { popularMovieResult ->
                _uiState.update { it.copy(popularMovieState = popularMovieResult) }
                when (popularMovieResult) {
                    Result.Loading -> {

                    }

                    is Result.Error -> {
                        // Handle error
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(listPopularMovie = popularMovieResult.data.dataList)
                        }
                    }
                }
            }
        }
    }

    fun getTopRatedMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getTopRatedMovie(language, apiKey)
                .collectLatest { topRatedMovieResult ->
                    _uiState.update { it.copy(topRatedMovieState = topRatedMovieResult) }
                    when (topRatedMovieResult) {
                        Result.Loading -> {

                        }

                        is Result.Error -> {
                            // Handle error
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listTopRatedMovie = topRatedMovieResult.data.dataList)
                            }
                        }
                    }
                }
        }
    }

    fun getUpComingMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getUpComingMovie(language, apiKey)
                .collectLatest { upComingMovieResult ->
                    _uiState.update { it.copy(upComingMovieState = upComingMovieResult) }
                    when (upComingMovieResult) {
                        Result.Loading -> {

                        }

                        is Result.Error -> {
                            // Handle error
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listUpComingMovie = upComingMovieResult.data.dataList)
                            }
                        }
                    }
                }
        }
    }

    private fun getMovieGenreList(language: String, apiKey: String) {
        viewModelScope.launch {
            genreRepository.getMovieGenreList(language = language, apiKey = apiKey)
                .collectLatest { genreResult ->
                    when (genreResult) {
                        Result.Loading -> {

                        }

                        is Result.Error -> {
                            // Handle error
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listGenre = genreResult.data.listGenre)
                            }
                        }
                    }
                }
        }
    }

    private fun getNowPlayingMovie(language: String, apiKey: String, page: Int) {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovie(language = language, apiKey = apiKey, page = page)
                .collectLatest { nowPlayingMovieResult ->
                    _uiState.update { it.copy(nowPlayingMovieState = nowPlayingMovieResult) }
                    when (nowPlayingMovieResult) {
                        Result.Loading -> {

                        }

                        is Result.Error -> {
                            // Handle error
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listNowPlayingMovie = nowPlayingMovieResult.data.dataList)
                            }
                        }
                    }
                }
        }
    }

    fun pullRefresh() {
        viewModelScope.launch {
            _refreshing.value = true
            delay(1500)
            refreshData()
            _refreshing.value = false
        }
    }

    fun refreshData() {
        getTrendingMovie(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
        getPopularMovie(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
        getTopRatedMovie(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
        getUpComingMovie(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
        getMovieGenreList(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
        getNowPlayingMovie(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY, 1)
    }

}