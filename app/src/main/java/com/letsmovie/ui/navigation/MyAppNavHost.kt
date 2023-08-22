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
import com.letsmovie.ui.movie.MovieUI
import com.letsmovie.ui.movie.movebygenre.MovieByGenreUI
import com.letsmovie.ui.movie.moviebysearch.MovieBySearchUI
import com.letsmovie.ui.movie.moviebytype.MovieByTypeUI
import com.letsmovie.ui.movie.moviedetail.MovieDetailUI
import com.letsmovie.ui.setting.SettingUI
import com.letsmovie.ui.tv.TvUI
import com.letsmovie.ui.tv.tvdetail.TvDetailUI

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
        favGraph(navController)
        settingGraph(navController)
    }
}

fun NavGraphBuilder.movieGraph(navController: NavHostController) {
    navigation(
        startDestination = TopLevelDestination.MOVIE.startDestination,
        route = TopLevelDestination.MOVIE.route
    ) {

        // Movie home screen
        composable(route = MovieDestination.route) {
            MovieUI(
                movieViewModel = hiltViewModel(),
                onMovieClickDetail = { movieId ->
                    navController.navigate(
                        MovieDetailDestination.createNavRoute(movieId)
                    )
                },
                onGenreClick = { genreId ->
                    navController.navigate(
                        MovieByGenreDestination.createNavRoute(genreId)
                    )
                },
                onMovieViewMoreClick = { movieType ->
                    navController.navigate(
                        MovieByTypeDestination.createNavRoute(movieType)
                    )
                },
                onSearchBarClick = {
                    navController.navigate(
                        MovieBySearchDestination.createNavRoute()
                    )
                }
            )
        }

        // Movie detail screen
        composable(
            route = MovieDetailDestination.route,
            arguments = MovieDetailDestination.listArgument
        ) {
            MovieDetailUI(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                movieDetailViewModel = hiltViewModel(),
                onClickBack = {
                    navController.popBackStack()
                }
            )
        }

        // Movie by genre screen
        composable(
            route = MovieByGenreDestination.route,
            arguments = MovieByGenreDestination.listArgument
        ) {
            MovieByGenreUI(
                movieByGenreViewModel = hiltViewModel(),
                onMovieClick = { movieId ->
                    navController.navigate(MovieDetailDestination.createNavRoute(movieId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Movie by type screen
        composable(
            route = MovieByTypeDestination.route,
            arguments = MovieByTypeDestination.listArgument
        ){
            MovieByTypeUI(
                movieByTypeViewModel = hiltViewModel(),
                onMovieClick = { movieId ->
                  navController.navigate(MovieDetailDestination.createNavRoute(movieId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = MovieBySearchDestination.route
        ){
            MovieBySearchUI()
        }
    }
}

fun NavGraphBuilder.tvGraph(navController: NavHostController) {
    navigation(
        startDestination = TopLevelDestination.TV.startDestination,
        route = TopLevelDestination.TV.route
    ) {
        composable(route = TvDestination.route) {
            TvUI(
                tvViewModel = hiltViewModel(),
                onTvClick = { tvId ->
                    navController.navigate(TvDetailDestination.createNavRoute(tvId))
                }
            )
        }
        composable(route = TvDetailDestination.route) {
            TvDetailUI(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                tvViewModel = hiltViewModel(),
                onClickBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavGraphBuilder.favGraph(navController: NavHostController) {
    navigation(
        startDestination = TopLevelDestination.FAVOURITE.startDestination,
        route = TopLevelDestination.FAVOURITE.route
    ) {
        composable(FavoriteDestination.route) {
            FavouriteUI(
                movieViewModel = hiltViewModel(),
                tvViewModel = hiltViewModel(),
                onTvClick = { tvId ->
                    navController.navigate(TvDetailDestination.createNavRoute(tvId))
                },
                onMovieClick = { movieId ->
                    navController.navigate(MovieDetailDestination.createNavRoute(movieId))
                }
            )
        }
    }
}

fun NavGraphBuilder.settingGraph(navController: NavHostController) {
    navigation(
        startDestination = TopLevelDestination.SETTING.startDestination,
        route = TopLevelDestination.SETTING.route
    ) {
        composable(SettingDestination.route) {
            SettingUI()
        }
    }
}