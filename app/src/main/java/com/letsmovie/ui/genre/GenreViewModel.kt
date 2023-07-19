package com.letsmovie.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.DataGenreResponse
import com.letsmovie.model.Result
import com.letsmovie.repository.GenreRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreRepository: GenreRepository
): ViewModel() {
    private val _movieGenre: MutableStateFlow<Result<DataGenreResponse>>
    = MutableStateFlow(Result.Loading)
    val movieGenre: StateFlow<Result<DataGenreResponse>> = _movieGenre.asStateFlow()
    init {
        getMovieGenreList("vi", Define.API_KEY)
    }

    private fun getMovieGenreList(language: String, apiKey: String){
        viewModelScope.launch {
            genreRepository.getMovieGenreList(language = language, apiKey = apiKey).collectLatest {
                _movieGenre.value = it
            }
        }
    }


}