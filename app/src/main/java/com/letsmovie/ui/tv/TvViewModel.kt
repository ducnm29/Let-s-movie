package com.letsmovie.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsmovie.BuildConfig
import com.letsmovie.data.api.movie.DataListResponse
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
    val trendingTvStateFlow: StateFlow<Result<DataListResponse<Tv>>> =
        _trendingTvStateFlow.asStateFlow()

    private val _popularTvStateFlow: MutableStateFlow<Result<DataListResponse<Tv>>> =
        MutableStateFlow(Result.Loading)
    val popularTvStateFlow: StateFlow<Result<DataListResponse<Tv>>> =
        _popularTvStateFlow.asStateFlow()


    init {
        getTrendingTv()
        getPopularTv()
    }

    fun getTrendingTv() {
        viewModelScope.launch {
            tvRepository.getTrendingTv(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY).collectLatest {
                _trendingTvStateFlow.value = it
            }
        }
    }

    fun getPopularTv() {
        viewModelScope.launch {
            tvRepository.getPopularTv(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY).collectLatest {
                _popularTvStateFlow.value = it
            }
        }
    }
}