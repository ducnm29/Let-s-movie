package com.letsmovie.ui.movie

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.repository.MovieRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _trendingMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _popularMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _topRatedMovieStateFlow: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    private val _movieDetail: MutableStateFlow<Result<Movie>> = MutableStateFlow(Result.Loading)
    val trendingMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _trendingMovieStateFlow.asStateFlow()
    val popularMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _popularMovieStateFlow.asStateFlow()
    val topRatedMovieStateFlow: StateFlow<Result<DataListResponse<Movie>>> =
        _topRatedMovieStateFlow.asStateFlow()
    val movieDetail: StateFlow<Result<Movie>> = _movieDetail.asStateFlow()

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

    fun getTopRatedMovie(language: String, apiKey: String){
        viewModelScope.launch {
            movieRepository.getTopRatedMovie(language, apiKey).collectLatest {
                _topRatedMovieStateFlow.value = it
            }
        }
    }

    fun getMovieDetail(movieId: String, language: String, apiKey: String) {
        viewModelScope.launch {
            _movieDetail.emit(Result.Loading)
            movieRepository.getMovieDetail(movieId = movieId, language = language, apiKey = apiKey)
                .catch {
                    _movieDetail.emit(Result.Error(it.toString()))
                    Log.w(Define.ERROR_TAG, it.toString())
                }
                .collect { data ->
                    _movieDetail.emit(Result.Success(data))
                }
        }
    }

    fun pullRefresh(){
        viewModelScope.launch {
            _refreshing.value = true
            delay(1500)
            refreshData()
            _refreshing.value = false
        }
    }
    private fun refreshData(){
        getTrendingMovie("vi", Define.API_KEY)
        getPopularMovie("vi", Define.API_KEY)
        getTopRatedMovie("vi", Define.API_KEY)
    }

}