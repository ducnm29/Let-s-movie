package com.letsmovie.ui.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.letsmovie.R
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.MovieCarousel
import com.letsmovie.ui.component.ListGenreUI
import com.letsmovie.ui.component.ListMovie
import com.letsmovie.ui.component.ProgressLoading
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.theme.background_card_item
import com.letsmovie.util.Define

@Composable
fun MovieUI(
    movieViewModel: MovieViewModel,
    onMovieClickDetail: (String) -> Unit,
    onGenreClick: (String, String) -> Unit,
    onMovieViewMoreClick: (String) -> Unit,
    onSearchBarClick: () -> Unit
) {

    val uiState by movieViewModel.uiState.collectAsStateWithLifecycle()

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
            MovieCarousel(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical0)),
                listMovie = uiState.listNowPlayingMovie,
                onClick = onMovieClickDetail
            )
            ListGenreUI(
                listGenre = uiState.listGenre,
                onGenreClick = onGenreClick
            )
            ListMovie(
                listMovie = uiState.listTrendingMovie,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical3)),
                listMovieName = stringResource(id = R.string.trending_title),
                onClick = onMovieClickDetail
            )
            ListMovie(
                listMovie = uiState.listPopularMovie,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical3)),
                listMovieName = stringResource(id = R.string.popular_title),
                onClick = onMovieClickDetail
            )
            ListMovieDetailUI(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical3)),
                categoryType = stringResource(id = R.string.top_rated_title),
                listMovie = uiState.listTopRatedMovie,
                onclick = onMovieClickDetail,
                onViewMoreClick = {
                    onMovieViewMoreClick(Define.TOP_RATED_MOVIE)
                }
            )
            ListMovieDetailUI(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacer_vertical3)),
                categoryType = stringResource(id = R.string.up_coming_title),
                listMovie = uiState.listUpComingMovie,
                onclick = onMovieClickDetail,
                onViewMoreClick = {
                    onMovieViewMoreClick(Define.UP_COMING_MOVIE)
                }
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_vertical1)))
        }

//        if (uiState.isLoading) {
//            ProgressLoading(
//                modifier = Modifier
//                    .zIndex(1f)
//                    .fillMaxSize()
//                    .alpha(0.5f)
//                    .background(MaterialTheme.colorScheme.secondaryContainer)
//            )
//        }
    }
}

