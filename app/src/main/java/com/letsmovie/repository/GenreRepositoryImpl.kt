package com.letsmovie.repository

import com.letsmovie.data.api.genre.GenreApi
import com.letsmovie.data.api.genre.DataGenreResponse
import com.letsmovie.data.api.genre.toModel
import com.letsmovie.model.Genre
import com.letsmovie.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl(
    private val genreApi: GenreApi
) : GenreRepository {
    override fun getTvGenreList(language: String, apiKey: String): Flow<Result<List<Genre>>> {
        return flow {
            emit(Result.Loading)
            val data = genreApi.getTvGenre(language = language, apiKey = apiKey)
            emit(Result.Success(data.listGenre.map { it.toModel() }))
        }.catch {
            emit(Result.Error(it.toString()))
        }

    }

    override fun getMovieGenreList(language: String, apiKey: String): Flow<Result<List<Genre>>> {
        return flow {
            emit(Result.Loading)
            val data = genreApi.getMovieGenre(language = language, apiKey = apiKey)
            emit(Result.Success(data.listGenre.map { it.toModel() }))
        }.catch {
            emit(Result.Error(it.toString()))
        }

    }
}