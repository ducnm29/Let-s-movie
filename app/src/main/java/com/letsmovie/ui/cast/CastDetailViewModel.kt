package com.letsmovie.ui.cast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.BuildConfig
import com.letsmovie.model.Result
import com.letsmovie.repository.CastRepository
import com.letsmovie.ui.navigation.CastDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val castRepository: CastRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CastDetailUiState.EMPTY)
    val uiState: StateFlow<CastDetailUiState> = _uiState

    private val castId = savedStateHandle[CastDetailDestination.castId] ?: ""

    init {
        getCastDetail(
            castId = castId,
            apiKey = BuildConfig.API_KEY
        )
    }

    private fun getCastDetail(castId: String, apiKey: String) {
        viewModelScope.launch {
            castRepository.getCastDetail(
                personId = castId,
                apiKey = apiKey
            ).collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        // handle later
                    }

                    is Result.Loading -> {
                        // do nothing
                    }

                    is Result.Success -> {
                        _uiState.update { it.copy(credit = result.data) }
                    }
                }
            }
        }
    }
}