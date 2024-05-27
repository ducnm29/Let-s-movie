package com.letsmovie.ui.genre

import androidx.lifecycle.ViewModel
import com.letsmovie.data.network.genre.DataGenreResponse
import com.letsmovie.model.Result
import com.letsmovie.repository.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val _movieGenre: MutableStateFlow<Result<DataGenreResponse>> =
        MutableStateFlow(Result.Loading)
    val movieGenre: StateFlow<Result<DataGenreResponse>> = _movieGenre.asStateFlow()


}