package com.letsmovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.letsmovie.model.Movie
import com.letsmovie.repository.MovieRepository
import com.letsmovie.util.Define

class MovieByTypePagingSource(
    private val language: String,
    private val apiKey: String,
    private val movieType: String,
    private val movieRepository: MovieRepository
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val data = when(movieType){
                Define.TOP_RATED_MOVIE -> {
                    movieRepository.getTopRatedMovie(
                        language = language,
                        apiKey = apiKey,
                        page = currentPage
                    )
                }
                else -> {
                    movieRepository.getUpComingMovie(
                        language = language,
                        apiKey = apiKey,
                        page = currentPage
                    )
                }
            }
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