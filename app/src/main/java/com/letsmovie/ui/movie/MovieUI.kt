package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.ImageCarousel
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.navigation.BaseScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieUI(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    movieViewModel: MovieViewModel
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState()
    val popularMovieResult = movieViewModel.popularMovieStateFlow.collectAsState()

    val pullState = rememberPullRefreshState(
        refreshing = movieViewModel.refreshing.value,
        onRefresh = { movieViewModel.pullRefresh() }
    )
    Box(
        modifier = Modifier
            .pullRefresh(
                state = pullState,
                enabled = true
            )
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = rememberScrollState())
        ) {
            PullRefreshIndicator(
                refreshing = movieViewModel.refreshing.value,
                state = pullState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            HeaderUserInfoUI()
            SearchBarUI()
            ImageCarousel(
                trendingMovieResult.value
            )
            ListItemWithData(
                result = trendingMovieResult.value,
                modifier = modifier,
                navHostController = navHostController,
                categoryName = "Trending",
                onClick = { movieId ->
                    navHostController.navigate(BaseScreen.MovieDetailScreen.route + "/" + movieId)
                }
            )
            ListItemWithData(
                result = popularMovieResult.value,
                modifier = modifier,
                navHostController = navHostController,
                categoryName = "Popular",
                onClick = { movieId ->
                    navHostController.navigate(BaseScreen.MovieDetailScreen.route + "/" + movieId)
                }
            )
        }
    }
}

