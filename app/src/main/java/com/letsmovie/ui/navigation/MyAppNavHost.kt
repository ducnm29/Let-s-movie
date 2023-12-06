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
import com.letsmovie.BuildConfig
import com.letsmovie.ui.favourite.FavouriteUI
import com.letsmovie.ui.movie.MovieUI
import com.letsmovie.ui.movie.movebygenre.MovieByGenreUI
import com.letsmovie.ui.movie.moviebysearch.MovieBySearchUI
import com.letsmovie.ui.movie.moviebytype.MovieByTypeUI
import com.letsmovie.ui.movie.moviedetail.MovieDetailUI
import com.letsmovie.ui.setting.SettingUI
import com.letsmovie.ui.tv.TvUI
import com.letsmovie.ui.tv.tvdetail.TvDetailUI
import com.letsmovie.util.Define

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    onClickBack: () -> Unit,
    onNavigateScreen: (String) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        movieGraph(
            onNavigateScreen = onNavigateScreen,
            onClickBack = onClickBack
        )
        tvGraph(
            onNavigateScreen = onNavigateScreen,
            onClickBack = onClickBack
        )
        favGraph(
            onNavigateScreen = onNavigateScreen
        )
        settingGraph()
    }
}

fun NavGraphBuilder.movieGraph(
    onClickBack: () -> Unit,
    onNavigateScreen: (String) -> Unit
) {
    navigation(
        startDestination = TopLevelDestination.MOVIE.startDestination,
        route = TopLevelDestination.MOVIE.route
    ) {

        // Movie home screen
        composable(route = MovieDestination.route) {
            MovieUI(
                movieViewModel = hiltViewModel(),
                onMovieClickDetail = { movieId ->
                    onNavigateScreen(MovieDetailDestination.createNavRoute(movieId))
                },
                onGenreClick = { genreId, genreName ->
                    onNavigateScreen(MovieByGenreDestination.createNavRoute(genreId, genreName))
                },
                onMovieViewMoreClick = { movieType ->
                    onNavigateScreen(MovieByTypeDestination.createNavRoute(movieType))
                },
                onSearchBarClick = {
                    onNavigateScreen(MovieBySearchDestination.createNavRoute())
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
                onClickBack = onClickBack,
                onMovieClickDetail = { movieId ->
                    onNavigateScreen(MovieDetailDestination.createNavRoute(movieId))
                },
                onGenreClick = { genreId, genreName ->
                    onNavigateScreen(
                        MovieByGenreDestination.createNavRoute(genreId, genreName)
                    )
                }
            )
        }

        // Movie by genre screen
        composable(
            route = MovieByGenreDestination.route,
            arguments = MovieByGenreDestination.listArgument
        ) { backStackEntry ->
            MovieByGenreUI(
                movieByGenreViewModel = hiltViewModel(),
                genreName = backStackEntry.arguments?.getString(MovieByGenreDestination.genreNameArg)
                    ?: "",
                onMovieClick = { movieId ->
                    onNavigateScreen(MovieDetailDestination.createNavRoute(movieId))
                },
                onBackClick = onClickBack
            )
        }

        // Movie by type screen
        composable(
            route = MovieByTypeDestination.route,
            arguments = MovieByTypeDestination.listArgument
        ) { backStackEntry ->
            MovieByTypeUI(
                movieByTypeViewModel = hiltViewModel(),
                typeName = backStackEntry.arguments?.getString(MovieByTypeDestination.movieType)
                    ?: "",
                onMovieClick = { movieId ->
                    onNavigateScreen(MovieDetailDestination.createNavRoute(movieId))
                },
                onBackClick = onClickBack
            )
        }

        composable(
            route = MovieBySearchDestination.route
        ) {
            MovieBySearchUI(
                movieBySearchViewModel = hiltViewModel(),
                onBackClick = onClickBack,
                onMovieDetailClick = { movieId ->
                    onNavigateScreen(MovieDetailDestination.createNavRoute(movieId))
                },
                onSearchWithKeyWord = { movieBySearchViewModel, keyword ->
                    movieBySearchViewModel.setKeyWordSearch(keyword)
                    movieBySearchViewModel.getMovieSearch(
                        language = Define.LANGUAGE_DEFAULT,
                        apiKey = BuildConfig.API_KEY,
                        includeAdult = false
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.tvGraph(
    onNavigateScreen: (String) -> Unit,
    onClickBack: () -> Unit
) {
    navigation(
        startDestination = TopLevelDestination.TV.startDestination,
        route = TopLevelDestination.TV.route
    ) {
        composable(route = TvDestination.route) {
            TvUI(
                tvViewModel = hiltViewModel(),
                onTvClick = { tvId ->
                    onNavigateScreen(TvDetailDestination.createNavRoute(tvId))
                }
            )
        }
        composable(route = TvDetailDestination.route) {
            TvDetailUI(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                tvViewModel = hiltViewModel(),
                onClickBack = onClickBack
            )
        }
    }
}

fun NavGraphBuilder.favGraph(
    onNavigateScreen: (String) -> Unit
) {
    navigation(
        startDestination = TopLevelDestination.FAVOURITE.startDestination,
        route = TopLevelDestination.FAVOURITE.route
    ) {
        composable(FavoriteDestination.route) {
            FavouriteUI(
                movieViewModel = hiltViewModel(),
                tvViewModel = hiltViewModel(),
                onTvClick = { tvId ->
                    onNavigateScreen(TvDetailDestination.createNavRoute(tvId))
                },
                onMovieClick = { movieId ->
                    onNavigateScreen(MovieDetailDestination.createNavRoute(movieId))
                }
            )
        }
    }
}

fun NavGraphBuilder.settingGraph() {
    navigation(
        startDestination = TopLevelDestination.SETTING.startDestination,
        route = TopLevelDestination.SETTING.route
    ) {
        composable(SettingDestination.route) {
            SettingUI()
        }
    }
}