package com.letsmovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.letsmovie.model.Movie
import com.letsmovie.repository.MovieRepository

class MovieBySearchPagingSource(
    private val movieRepository: MovieRepository,
    private val language: String,
    private val apiKey: String,
    private val includeAdult: Boolean,
    private val searchKey: String
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage = params.key ?: 1
        return try {
            val data = movieRepository.getSearchMovie(
                language = language,
                apiKey = apiKey,
                includeAdult = includeAdult,
                searchKey = searchKey,
                page = currentPage
            )
            LoadResult.Page(
                data = data.dataList,
                prevKey = null,
                nextKey = currentPage + 1
            )
        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }
}