package com.letsmovie.ui.movie.moveingenre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.repository.MovieRepository
import com.letsmovie.ui.navigation.MovieInGenreDestination
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInGenreViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val genreId: String = savedStateHandle[MovieInGenreDestination.genreIDArg] ?: ""

    private val _movieInGenre: MutableStateFlow<Result<DataListResponse<Movie>>> =
        MutableStateFlow(Result.Loading)
    val movieInGenre: StateFlow<Result<DataListResponse<Movie>>> = _movieInGenre.asStateFlow()

    init {
        getMovieInGenre(
            language = Define.LANGUAGE_DEFAULT,
            apiKey = Define.API_KEY,
            genreId = genreId
        )
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
}