package com.letsmovie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.MovieResponse
import com.letsmovie.model.Result
import com.letsmovie.repository.MovieRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _trendingMovieStateFlow: MutableStateFlow<Result<MovieResponse>>
    = MutableStateFlow(Result.Loading)
    private val _popularMovieStateFlow: MutableStateFlow<Result<MovieResponse>>
    = MutableStateFlow(Result.Loading)
    val trendingMovieStateFlow: StateFlow<Result<MovieResponse>> = _trendingMovieStateFlow.asStateFlow()
    val popularMovieStateFlow: StateFlow<Result<MovieResponse>> = _popularMovieStateFlow.asStateFlow()
    fun getTrendingMovie(){
        viewModelScope.launch {
            _trendingMovieStateFlow.emit(Result.Loading)
            movieRepository.getTrendingMovie("vi",Define.API_KEY)
                .catch {
                    _trendingMovieStateFlow.emit(Result.Error(it.toString()))
                }
                .collect{data ->
                    _trendingMovieStateFlow.emit(Result.Success(data))
                }
        }
    }
    fun getPopularMovie(){
        viewModelScope.launch {
            _popularMovieStateFlow.emit(Result.Loading)
            movieRepository.getPopularMovie("vi", Define.API_KEY)
                .catch {
                    _popularMovieStateFlow.emit(Result.Error(it.toString()))
                }
                .collect{data ->
                    _popularMovieStateFlow.emit(Result.Success(data))
                }
        }
    }
}