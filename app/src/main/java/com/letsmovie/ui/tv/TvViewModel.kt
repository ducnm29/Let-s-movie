package com.letsmovie.ui.tv

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvRepository: TvRepository
): ViewModel() {
    private val _trendingTvStateFlow: MutableStateFlow<Result<DataListResponse<Tv>>>
    = MutableStateFlow(Result.Loading)
    private val _popularTvStateFlow: MutableStateFlow<Result<DataListResponse<Tv>>>
    = MutableStateFlow(Result.Loading)
    private val _tvDetailStateFlow: MutableStateFlow<Result<Tv>>
    = MutableStateFlow(Result.Loading)
    val trendingTvStateFlow: StateFlow<Result<DataListResponse<Tv>>> = _trendingTvStateFlow.asStateFlow()
    val popularTvStateFlow: StateFlow<Result<DataListResponse<Tv>>> = _popularTvStateFlow.asStateFlow()
    val tvDetailStateFlow: StateFlow<Result<Tv>> = _tvDetailStateFlow.asStateFlow()

    fun getTrendingTv(){
        viewModelScope.launch {
            _trendingTvStateFlow.value = Result.Loading
            tvRepository.getTrendingTv("en", Define.API_KEY)
                .catch {error ->
                    _trendingTvStateFlow.emit(Result.Error(error.toString()))
                }
                .collect{data ->
                    _trendingTvStateFlow.emit(Result.Success(data))
                }
        }
    }
    fun getPopularTv(){
        viewModelScope.launch {
            _popularTvStateFlow.value = Result.Loading
            tvRepository.getPopularTv("vi", Define.API_KEY)
                .catch {error ->
                    _popularTvStateFlow.emit(Result.Error(error.toString()))
                }
                .collect{data ->
                    _popularTvStateFlow.emit(Result.Success(data))
                }
        }
    }
    fun getTvDetail(tvId: String, language: String, apiKey: String){
        viewModelScope.launch {
            _tvDetailStateFlow.emit(Result.Loading)
            tvRepository.getTvDetail(tvId = tvId, language = "vi", apiKey = Define.API_KEY)
                .catch { error ->
                    _tvDetailStateFlow.emit(Result.Error(error.toString()))
                }
                .collect{ data ->
                    _tvDetailStateFlow.emit(Result.Success(data))
                }
        }
    }
}