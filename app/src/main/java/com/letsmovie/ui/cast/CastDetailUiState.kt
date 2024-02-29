package com.letsmovie.ui.cast

import com.letsmovie.model.CastImage
import com.letsmovie.model.Credit
import com.letsmovie.model.Movie

data class CastDetailUiState(
    val credit: Credit = Credit.EMPTY,
    val listMovieRelated: List<Movie> = listOf(),
    val listCastImage: List<CastImage> = listOf()
) {
    val backdropImage: String
        get() = if(listCastImage.isNotEmpty()) listCastImage.last().filePath else ""
    companion object {
        val EMPTY = CastDetailUiState()
    }
}
