package com.letsmovie.ui.movie.moviebysearch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.letsmovie.model.Movie
import com.letsmovie.paging.MovieBySearchPagingSource
import com.letsmovie.repository.MovieRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieBySearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // State hold search value in MovieBySearch screen
    private val _keywordState: MutableState<String> = mutableStateOf("")
    val keywordState: State<String> = _keywordState

    //
    private val _movieSearchStateFlow: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val movieSearchStateFlow: StateFlow<PagingData<Movie>> = _movieSearchStateFlow.asStateFlow()

    fun getMovieSearch(language: String, apiKey: String, includeAdult: Boolean) {
        val pager = Pager(
            config = PagingConfig(pageSize = Define.MAX_PAGING_SIZE),
            pagingSourceFactory = {
                MovieBySearchPagingSource(
                    movieRepository = movieRepository,
                    language = language,
                    apiKey = apiKey,
                    includeAdult = includeAdult,
                    searchKey = keywordState.value
                )
            },
            initialKey = 1
        ).flow.cachedIn(viewModelScope)

        viewModelScope.launch {
            pager.collectLatest {
                _movieSearchStateFlow.value = it
            }
        }
    }

    fun setKeyWordSearch(keyWord: String){
        _keywordState.value = keyWord
    }


}