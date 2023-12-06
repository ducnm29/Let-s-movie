package com.letsmovie.ui.tv.tvdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.BuildConfig
import com.letsmovie.model.Result
import com.letsmovie.model.Tv
import com.letsmovie.repository.TvRepository
import com.letsmovie.ui.navigation.TvDetailDestination
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val tvId: String = savedStateHandle[TvDetailDestination.tvIdArgs] ?: ""

    private val _tvDetailStateFlow: MutableStateFlow<Result<Tv>> = MutableStateFlow(Result.Loading)
    val tvDetailStateFlow: StateFlow<Result<Tv>> = _tvDetailStateFlow.asStateFlow()

    init {
        getTvDetail(
            tvId = tvId,
            language = Define.LANGUAGE_DEFAULT,
            apiKey = BuildConfig.API_KEY
        )
    }

    fun getTvDetail(tvId: String, language: String, apiKey: String) {
        viewModelScope.launch {
            tvRepository.getTvDetail(
                tvId = tvId,
                language = language,
                apiKey = apiKey
            ).collectLatest {
                _tvDetailStateFlow.value = it
            }
        }
    }
}