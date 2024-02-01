package com.letsmovie.repository

import com.letsmovie.data.api.genre.DataGenreResponse
import com.letsmovie.model.Genre
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getTvGenreList(language: String, apiKey: String): Flow<Result<List<Genre>>>
    fun getMovieGenreList(language: String, apiKey: String): Flow<Result<List<Genre>>>
}