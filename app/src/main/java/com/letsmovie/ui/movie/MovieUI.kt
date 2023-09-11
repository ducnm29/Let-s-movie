package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.letsmovie.R
import com.letsmovie.model.Result
import com.letsmovie.ui.component.ErrorUI
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.ImageCarousel
import com.letsmovie.ui.component.ListGenreUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.util.Define

@Composable
fun MovieUI(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel,
    onMovieClickDetail: (String) -> Unit,
    onGenreClick: (String, String) -> Unit,
    onMovieViewMoreClick: (String) -> Unit,
    onSearchBarClick: () -> Unit
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState()
    val popularMovieResult = movieViewModel.popularMovieStateFlow.collectAsState()
    val topRatedMovieResult = movieViewModel.topRatedMovieStateFlow.collectAsState()
    val upComingMovieResult = movieViewModel.upComingMovie.collectAsState()
    val movieGenreList = movieViewModel.movieGenre.collectAsState()
    val nowPlayingMovieResult = movieViewModel.nowPlayingMovieStateFlow.collectAsState()

    when (nowPlayingMovieResult.value) {
        is Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Error -> {
            ErrorUI(
                modifier = Modifier.fillMaxSize(),
                onRetry = {
                    movieViewModel.refreshData()
                },
                result = nowPlayingMovieResult.value as Result.Error
            )
        }

        is Result.Success -> {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.verticalScroll(state = rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical1)))
                    HeaderUserInfoUI()
                    SearchBarUI(
                        onClick = onSearchBarClick
                    )
                    ImageCarousel(
                        result = nowPlayingMovieResult.value,
                        onClick = onMovieClickDetail
                    )
                    ListGenreUI(
                        listGenreResult = movieGenreList.value,
                        onGenreClick = onGenreClick
                    )
                    ListItemWithData(
                        result = trendingMovieResult.value,
                        modifier = modifier,
                        categoryName = stringResource(id = R.string.trending_title),
                        onClick = onMovieClickDetail
                    )
                    ListItemWithData(
                        result = popularMovieResult.value,
                        modifier = modifier,
                        categoryName = stringResource(id = R.string.popular_title),
                        onClick = onMovieClickDetail
                    )
                    ListMovieDetailUI(
                        categoryType = stringResource(id = R.string.top_rated_title),
                        listMovieResult = topRatedMovieResult.value,
                        onclick = onMovieClickDetail,
                        onViewMoreClick = {
                            onMovieViewMoreClick(Define.TOP_RATED_MOVIE)
                        }
                    )
                    ListMovieDetailUI(
                        categoryType = stringResource(id = R.string.up_coming_title),
                        listMovieResult = upComingMovieResult.value,
                        onclick = onMovieClickDetail,
                        onViewMoreClick = {
                            onMovieViewMoreClick(Define.UP_COMING_MOVIE)
                        }
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_vertical1)))
                }
            }
        }
    }

}

