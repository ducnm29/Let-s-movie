package com.letsmovie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.letsmovie.ui.favourite.FavouriteUI
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
        composable(BaseScreen.MovieScreen.route){
            MovieUI()
        }
        composable(BaseScreen.TvScreen.route){
            TvUI()
        }
        composable(BaseScreen.FavouriteScreen.route){
            FavouriteUI()
        }
        composable(BaseScreen.SettingScreen.route){
            SettingUI()
        }
    }
}