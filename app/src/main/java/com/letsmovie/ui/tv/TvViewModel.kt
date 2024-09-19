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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvUiState.EMPTY)
    val uiState: StateFlow<TvUiState> get() = _uiState.asStateFlow()


    init {
        getTrendingTv()
        getPopularTv()
    }

    fun getTrendingTv() {
        viewModelScope.launch {
            tvRepository.getTrendingTv(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            // handle error
                        }

                        Result.Loading -> {
                            // handle later
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listTrendingTv = result.data.dataList)
                            }
                        }
                    }
                }
        }
    }

    fun getPopularTv() {
        viewModelScope.launch {
            tvRepository.getPopularTv(Define.LANGUAGE_DEFAULT, BuildConfig.API_KEY)
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            // handle error
                        }

                        Result.Loading -> {
                            // handle later
                        }

                        is Result.Success -> {
                            _uiState.update {
                                it.copy(listPopularTv = result.data.dataList)
                            }
                        }
                    }
                }
        }
    }
}