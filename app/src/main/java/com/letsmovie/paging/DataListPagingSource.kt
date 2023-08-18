package com.letsmovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.letsmovie.model.Movie
import com.letsmovie.repository.MovieRepository

class DataListPagingSource(
    private val movieRepository: MovieRepository,
    private val apiKey: String,
    private val genreId: String,
    private val language: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        // Load data from current page, if this value is null -> load page 1
        return try {
            val currentPage = params.key ?: 1
            val data = movieRepository.getMovieInGenre(
                language = language,
                apiKey = apiKey,
                genreId = genreId,
                page = currentPage
            )
            LoadResult.Page(
                data = data.dataList,
                prevKey = null,
                nextKey = currentPage + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}