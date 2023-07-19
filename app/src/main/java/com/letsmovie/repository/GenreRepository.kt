package com.letsmovie.repository

import com.letsmovie.model.DataGenreResponse
import com.letsmovie.model.Genre
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getTvGenreList(language: String, apiKey: String): Flow<Result<DataGenreResponse>>
    fun getMovieGenreList(language: String, apiKey: String): Flow<Result<DataGenreResponse>>
}