package com.letsmovie.ui.movie.movebygenre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.letsmovie.BuildConfig
import com.letsmovie.model.Movie
import com.letsmovie.paging.MovieByGenrePagingSource
import com.letsmovie.repository.MovieRepository
import com.letsmovie.ui.navigation.MovieByGenreDestination
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieByGenreViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val genreId: String = savedStateHandle[MovieByGenreDestination.genreIDArg] ?: ""

    private val _movieInGenre: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val movieInGenre: StateFlow<PagingData<Movie>> = _movieInGenre.asStateFlow()

    init {
        getMovieInGenre(
            language = Define.LANGUAGE_DEFAULT,
            apiKey = BuildConfig.API_KEY,
            genreId = genreId
        )
    }

    fun getMovieInGenre(language: String, apiKey: String, genreId: String) {
        val movieInGenreData =
            Pager(
                config = PagingConfig(pageSize = Define.MAX_PAGING_SIZE),
                pagingSourceFactory = {
                    MovieByGenrePagingSource(
                        movieRepository = movieRepository,
                        language = language,
                        apiKey = apiKey,
                        genreId = genreId
                    )
                }
            ).flow.cachedIn(viewModelScope)


        viewModelScope.launch {
            movieInGenreData.collectLatest {
                _movieInGenre.value = it
            }
        }
    }
}