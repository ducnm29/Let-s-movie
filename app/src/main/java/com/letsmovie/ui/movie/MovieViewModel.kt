package com.letsmovie.ui.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.DataGenreResponse
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.repository.GenreRepository
import com.letsmovie.repository.MovieRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val _trendingMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _popularMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _topRatedMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _upComingMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _movieDetail: MutableStateFlow<Result<Movie>> = MutableStateFlow(Result.Loading)
    private val _movieGenre: MutableStateFlow<Result<DataGenreResponse>> =
        MutableStateFlow(Result.Loading)
    private val _movieInGenre: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    val movieGenre: StateFlow<Result<DataGenreResponse>> = _movieGenre.asStateFlow()
    val trendingMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _trendingMovieStateFlow.asStateFlow()
    val popularMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _popularMovieStateFlow.asStateFlow()
    val topRatedMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _topRatedMovieStateFlow.asStateFlow()
    val upComingMovie: StateFlow<Result<DataListResponse<Movie>>> =
        _upComingMovieStateFlow.asStateFlow()
    val movieDetail: StateFlow<Result<Movie>> = _movieDetail.asStateFlow()
    val movieInGenre: StateFlow<Result<DataListResponse<Movie>>> = _movieInGenre.asStateFlow()

    // Pull to refresh
    private val _refreshing = mutableStateOf(false)
    var refreshing: State<Boolean> = _refreshing

    init {
        refreshData()
    }

    fun getTrendingMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getTrendingMovie(language, apiKey).collectLatest {
                _trendingMovieStateFlow.value = it
            }
        }
    }

    fun getPopularMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getPopularMovie(language, apiKey).collectLatest {
                _popularMovieStateFlow.value = it
            }
        }
    }

    fun getTopRatedMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getTopRatedMovie(language, apiKey).collectLatest {
                _topRatedMovieStateFlow.value = it
            }
        }
    }

    fun getUpComingMovie(language: String, apiKey: String) {
        viewModelScope.launch {
            movieRepository.getUpComingMovie(language, apiKey).collectLatest {
                _upComingMovieStateFlow.value = it
            }
        }
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

    private fun getMovieGenreList(language: String, apiKey: String) {
        viewModelScope.launch {
            genreRepository.getMovieGenreList(language = language, apiKey = apiKey).collectLatest {
                _movieGenre.value = it
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

    fun getMovieInGenre(language: String, apiKey: String, genreId: String) {
        viewModelScope.launch {
            movieRepository
                .getMovieInGenre(language = language, apiKey = apiKey, genreId = genreId)
                .collectLatest {
                    _movieInGenre.value = it
                }
        }
    }

    private fun refreshData() {
        getTrendingMovie(Define.LANGUAGE_DEFAULT, Define.API_KEY)
        getPopularMovie(Define.LANGUAGE_DEFAULT, Define.API_KEY)
        getTopRatedMovie(Define.LANGUAGE_DEFAULT, Define.API_KEY)
        getUpComingMovie(Define.LANGUAGE_DEFAULT, Define.API_KEY)
        getMovieGenreList(Define.LANGUAGE_DEFAULT, Define.API_KEY)
    }

    suspend fun test(): Int {
        var count = 0
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 50
        }
        val def = CoroutineScope(Dispatchers.IO).async {
            delay(3000)
            70
        }
        return count + def.await()
    }

}