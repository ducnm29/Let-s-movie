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
import com.letsmovie.util.Define
import com.letsmovie.model.Movie
import com.letsmovie.ui.favourite.FavouriteUI
import com.letsmovie.ui.movie.MovieDetailUI
import com.letsmovie.ui.movie.MovieUI
import com.letsmovie.ui.setting.SettingUI
import com.letsmovie.ui.tv.TvUI

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
    ){
        movieGraph(navController)
        composable(BaseScreen.TvScreen.route){
            TvUI(
                navHostController = navController,
                tvViewModel = hiltViewModel()
            )
        }
        composable(BaseScreen.FavouriteScreen.route){
            FavouriteUI(
                navHostController = navController,
                movieViewModel = hiltViewModel(),
                tvViewModel = hiltViewModel()
            )
        }
        composable(BaseScreen.SettingScreen.route){
            SettingUI()
        }
    }
}
fun NavGraphBuilder.movieGraph(navController: NavHostController){
    navigation(
        startDestination = BaseScreen.MovieScreen.route,
        route = Define.MOVIE_HOME
    ){
        composable(route = BaseScreen.MovieScreen.route){
            MovieUI(
                navHostController = navController,
                movieViewModel = hiltViewModel()
            )
        }
        composable(route = BaseScreen.MovieDetailScreen.route){
            MovieDetailUI(
                navHostController = navController,
                modifier = Modifier.verticalScroll(rememberScrollState()),
                movie = Movie("1","Fast and mad", "/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
                "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers," +
                        " he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. " +
                        "Can he let his past go and embrace his destiny to become a Knight of the Zodiac?", "/u17VLZqWFbeJsj1HpvB6QOOHvlC.jpg",
                    listOf(1,2),1f,"2022",1f,1,"movie")
            )
        }
    }
}