package com.letsmovie.ui.movie.moviebytype

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.letsmovie.BuildConfig
import com.letsmovie.model.Movie
import com.letsmovie.paging.MovieByTypePagingSource
import com.letsmovie.repository.MovieRepository
import com.letsmovie.ui.navigation.MovieByTypeDestination
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieByTypeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val movieType =
        savedStateHandle[MovieByTypeDestination.movieType] ?: Define.TOP_RATED_MOVIE
    private val _movieByTypeStateFlow: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val movieByTypeStateFlow: StateFlow<PagingData<Movie>> = _movieByTypeStateFlow.asStateFlow()

    init {
        getTopRatedMovies(
            language = Define.LANGUAGE_DEFAULT,
            apiKey = BuildConfig.API_KEY,
            movieType = movieType
        )
    }

    fun getTopRatedMovies(language: String, apiKey: String, movieType: String) {
        val dataPaging = Pager(
            config = PagingConfig(pageSize = Define.MAX_PAGING_SIZE),
            pagingSourceFactory = {
                MovieByTypePagingSource(
                    language = language,
                    apiKey = apiKey,
                    movieType = movieType,
                    movieRepository = movieRepository
                )
            },
            initialKey = 1
        ).flow.cachedIn(viewModelScope)

        viewModelScope.launch {
            dataPaging.collectLatest {
                _movieByTypeStateFlow.value = it
            }
        }
    }
}