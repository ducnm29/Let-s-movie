package com.letsmovie.ui.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.letsmovie.ui.component.ListItemWithData
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.movie.MovieViewModel

@Composable
fun FavouriteMovieUI(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel,
    onMovieClick: (String) -> Unit
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState()
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SearchBarUI()
        ListItemWithData(
            result = trendingMovieResult.value,
            modifier = modifier,
            categoryName = "Trending",
            onClick = onMovieClick
        )
    }
}