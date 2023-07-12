package com.letsmovie.ui.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.movie.MovieViewModel
import com.letsmovie.ui.navigation.BaseScreen
import com.letsmovie.util.Define

@Composable
fun FavouriteMovieUI(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel,
    navHostController: NavHostController
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState()
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
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
    }
}