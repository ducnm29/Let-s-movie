package com.letsmovie.ui.movie

//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.pullrefresh.PullRefreshIndicator
//import androidx.compose.material.pullrefresh.pullRefresh
//import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.letsmovie.R
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.ImageCarousel
import com.letsmovie.ui.component.ListGenreUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI

//@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieUI(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel,
    onMovieClickDetail: (String) -> Unit,
    onGenreClick: (String) -> Unit
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState()
    val popularMovieResult = movieViewModel.popularMovieStateFlow.collectAsState()
    val topRatedMovieResult = movieViewModel.topRatedMovieStateFlow.collectAsState()
    val upComingMovieResult = movieViewModel.upComingMovie.collectAsState()
    val movieGenreList = movieViewModel.movieGenre.collectAsState()

//    val pullState = rememberPullRefreshState(
//        refreshing = movieViewModel.refreshing.value,
//        onRefresh = { movieViewModel.pullRefresh() }
//    )
    Box(
        modifier = Modifier
//            .pullRefresh(
//                state = pullState,
//                enabled = true
//            )
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = rememberScrollState())
        ) {
//            PullRefreshIndicator(
//                refreshing = movieViewModel.refreshing.value,
//                state = pullState,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
            HeaderUserInfoUI()
            SearchBarUI()
            ImageCarousel(
                result = topRatedMovieResult.value,
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
                onclick = onMovieClickDetail
            )
            ListMovieDetailUI(
                categoryType = stringResource(id = R.string.up_coming_title),
                listMovieResult = upComingMovieResult.value,
                onclick = onMovieClickDetail
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_vertical1)))
        }
    }
}

