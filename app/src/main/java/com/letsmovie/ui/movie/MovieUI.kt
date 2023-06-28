package com.letsmovie.ui.movie

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.letsmovie.R
import com.letsmovie.model.MovieResponse
import com.letsmovie.model.Result
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.navigation.BaseScreen

@Composable
fun MovieUI(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    movieViewModel: MovieViewModel
) {
    val trendingMovieResult = movieViewModel.trendingMovieStateFlow.collectAsState().value
    val popularMovieResult = movieViewModel.popularMovieStateFlow.collectAsState().value
    LaunchedEffect(true){
        movieViewModel.getTrendingMovie()
        movieViewModel.getPopularMovie()
    }
    Column(
        modifier = Modifier.
        verticalScroll(rememberScrollState())
    ) {
        HeaderUserInfoUI()
        SearchBarUI()
        ListMovieWithData(
            movieResult =  trendingMovieResult,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Trending"
        )
        ListMovieWithData(
            movieResult =  popularMovieResult,
            modifier = modifier,
            navHostController = navHostController,
            categoryName = "Popular"
        )
    }
}

@Composable
fun ListMovieWithData(
    movieResult: Result<MovieResponse>,
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    categoryName: String
) {
    when(movieResult){
        is Result.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Result.Success -> {
            ListMovieUI(
                listMovieName = categoryName,
                onMovieClick = {
                    navHostController.navigate(BaseScreen.MovieDetailScreen.route)
                },
                listMovie = movieResult.data.movieList
            )
        }
        is Result.Error -> {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = stringResource(id = R.string.common_error))
                Spacer(modifier = Modifier.height(8.dp))
                Log.e("ex",movieResult.exception)
            }
        }
    }

}