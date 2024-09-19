package com.letsmovie.ui.tv

import com.letsmovie.model.Tv

data class TvUiState(
    val listTrendingTv: List<Tv> = listOf(),
    val listPopularTv: List<Tv> = listOf()
) {
    companion object {
        val EMPTY = TvUiState()
    }
}
