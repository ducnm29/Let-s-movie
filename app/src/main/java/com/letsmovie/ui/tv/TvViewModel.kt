package com.letsmovie.ui.tv

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Result
import com.letsmovie.model.Tv
import com.letsmovie.repository.TvRepository
import com.letsmovie.util.Define
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {
    private val _trendingTvStateFlow: MutableStateFlow<Result<DataListResponse<Tv>>> =
        MutableStateFlow(Result.Loading)
    private val _popularTvStateFlow: MutableStateFlow<Result<DataListResponse<Tv>>> =
        MutableStateFlow(Result.Loading)
    private val _tvDetailStateFlow: MutableStateFlow<Result<Tv>> = MutableStateFlow(Result.Loading)
    val trendingTvStateFlow: StateFlow<Result<DataListResponse<Tv>>> =
        _trendingTvStateFlow.asStateFlow()
    val popularTvStateFlow: StateFlow<Result<DataListResponse<Tv>>> =
        _popularTvStateFlow.asStateFlow()
    val tvDetailStateFlow: StateFlow<Result<Tv>> = _tvDetailStateFlow.asStateFlow()

    init {
        Log.d("TvViewModel", "init")
        getTrendingTv()
        getPopularTv()
    }

    fun getTrendingTv() {
        viewModelScope.launch {
            tvRepository.getTrendingTv(Define.LANGUAGE_DEFAULT, Define.API_KEY).collectLatest {
                _trendingTvStateFlow.value = it
            }
        }
    }

    fun getPopularTv() {
        viewModelScope.launch {
            tvRepository.getPopularTv(Define.LANGUAGE_DEFAULT, Define.API_KEY).collectLatest {
                _popularTvStateFlow.value = it
            }
        }
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