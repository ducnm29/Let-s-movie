package com.letsmovie.ui.navigation

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.letsmovie.ui.favourite.FavouriteUI
import com.letsmovie.ui.movie.MovieDetailUI
import com.letsmovie.ui.movie.MovieInGenreUI
import com.letsmovie.ui.movie.MovieUI
import com.letsmovie.ui.setting.SettingUI
import com.letsmovie.ui.tv.TvDetailUI
import com.letsmovie.ui.tv.TvUI
import com.letsmovie.util.Define

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        movieGraph(navController)
        tvGraph(navController)
        composable(BaseScreen.FavouriteScreen.route) {
            FavouriteUI(
                movieViewModel = hiltViewModel(),
                tvViewModel = hiltViewModel(),
                onTvClick = { tvId ->
                    navController.navigate(BaseScreen.TvDetailScreen.route + "/"+tvId)
                },
                onMovieClick = { movieId ->
                    navController.navigate(BaseScreen.MovieDetailScreen.route + "/"+movieId)
                }
            )
        }
        composable(BaseScreen.SettingScreen.route) {
            SettingUI()
        }
    }
}

fun NavGraphBuilder.movieGraph(navController: NavHostController) {
    navigation(
        startDestination = BaseScreen.MovieScreen.route,
        route = Define.MOVIE_HOME
    ) {
        composable(route = BaseScreen.MovieScreen.route) {
            MovieUI(
                movieViewModel = hiltViewModel(),
                onMovieClickDetail = { movieId ->
                    navController.navigate(BaseScreen.MovieDetailScreen.route + "/" + movieId)
                },
                onGenreClick = { genreId ->
                    navController.navigate(BaseScreen.MovieInGenreScreen.route + "/" + genreId)
                }
            )
        }
        composable(route = BaseScreen.MovieDetailScreen.route + "/{movieId}") { backStackEntry ->
            MovieDetailUI(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                movieId = backStackEntry.arguments?.getString("movieId") ?: "0",
                movieViewModel = hiltViewModel(),
                onClickBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = BaseScreen.MovieInGenreScreen.route + "/{genreId}") { backStackEntry ->
            MovieInGenreUI(
                movieViewModel = hiltViewModel(),
                genreId = backStackEntry.arguments?.getString("genreId") ?: "0",
                onMovieClick = { movieId ->
                    navController.navigate(BaseScreen.MovieDetailScreen.route + "/" + movieId)
                }
            )
        }
    }
}

fun NavGraphBuilder.tvGraph(navController: NavHostController) {
    navigation(
        startDestination = BaseScreen.TvScreen.route,
        route = Define.TV_HOME
    ) {
        composable(route = BaseScreen.TvScreen.route) {
            TvUI(
                tvViewModel = hiltViewModel(),
                onTvClick = { tvId ->
                    navController.navigate(BaseScreen.TvDetailScreen.route + "/"+tvId)
                }
            )
        }
        composable(route = BaseScreen.TvDetailScreen.route + "/{tvId}") { backStackKEntry ->
            TvDetailUI(
                navHostController = navController,
                modifier = Modifier.verticalScroll(rememberScrollState()),
                tvId = backStackKEntry.arguments?.getString("tvId") ?: "0",
                tvViewModel = hiltViewModel()
            )
        }
    }
}