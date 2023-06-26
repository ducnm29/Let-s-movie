package com.letsmovie.ui.navigation

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
        composable(BaseScreen.MovieScreen.route){
            MovieUI()
        }
        composable(BaseScreen.TvScreen.route){
            TvUI()
        }
        composable(BaseScreen.FavouriteScreen.route){
            FavouriteUI()
//            MovieDetailUI(
//                modifier = Modifier.verticalScroll(rememberScrollState()),
//                movie = Movie("1","Fast and mad", "Duc nm",
//                "https://picsum.photos/id/238/1600/1440", "Alodasjdahsgdahsgbdasdgashdgghasgsjgahahgsllllkkkkjhjkhdkjashdjhajksdhajkhsdkajshdajkdh" +
//                        "ajkshhhhhhhsjjjjjsdjkasdkjhsjdnajsdkjbcnbasbbcabcbajbshbdhabhsdasdgasdgasdguasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdfffbvhjbvdagsvbdjhjabsvdagjbahs"))
        }
        composable(BaseScreen.SettingScreen.route){
            SettingUI()
        }
    }
}