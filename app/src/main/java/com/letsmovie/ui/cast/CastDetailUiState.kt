package com.letsmovie.ui.cast

import com.letsmovie.model.Credit

data class CastDetailUiState(
    val credit: Credit = Credit.EMPTY
) {
    companion object {
        val EMPTY = CastDetailUiState()
    }
}
