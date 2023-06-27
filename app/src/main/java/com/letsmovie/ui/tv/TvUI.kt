package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.movie.ListMovieUI
import com.letsmovie.ui.navigation.BaseScreen

@Composable
fun TvUI(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.
        verticalScroll(rememberScrollState())
    ) {
        HeaderUserInfoUI()
        SearchBarUI()
        ListMovieUI(
            listMovieName = "VTV digital",
            onMovieClick = {
                navHostController.navigate(BaseScreen.MovieDetailScreen.route)
            }
        )
        ListMovieUI(
            listMovieName = "Trending",
            onMovieClick = {
                navHostController.navigate(BaseScreen.MovieDetailScreen.route)
            }
        )
    }
}