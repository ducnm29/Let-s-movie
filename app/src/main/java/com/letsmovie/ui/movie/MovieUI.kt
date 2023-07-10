package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.navigation.BaseScreen
import com.letsmovie.util.Define

@Composable
fun MovieUI(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    movieViewModel: MovieViewModel
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState()
    val popularMovieResult = movieViewModel.popularMovieStateFlow.collectAsState()
    LaunchedEffect(true){
        movieViewModel.getTrendingMovie("vi",Define.API_KEY)
        movieViewModel.getPopularMovie("vi", Define.API_KEY)
    }
    Column(
        modifier = Modifier.
        verticalScroll(rememberScrollState())
    ) {
        HeaderUserInfoUI()
        SearchBarUI()
        ListItemWithData(
            result =  trendingMovieResult.value,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Trending",
            onClick = {movieId ->
                navHostController.navigate(BaseScreen.MovieDetailScreen.route + "/"+movieId)
            }
        )
        ListItemWithData(
            result =  popularMovieResult.value,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Popular",
            onClick = {movieId ->
                navHostController.navigate(BaseScreen.MovieDetailScreen.route + "/"+movieId)
            }
        )
    }
}

