package com.letsmovie.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.sharp.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.letsmovie.util.Define

sealed class BaseScreen(val route: String, val name: String, val iconSelected: ImageVector, val iconNormal: ImageVector){
    object MovieScreen: BaseScreen(
        Define.MOVIE_ROUTE,
        Define.MOVIE_NAME,
        Icons.Default.Movie,
        Icons.Outlined.Movie
    )
    object FavouriteScreen: BaseScreen(
        Define.FAVOURITE_ROUTE,
        Define.FAVOURITE_NAME,
        Icons.Default.HeartBroken,
        Icons.Outlined.Favorite
    )
    object TvScreen: BaseScreen(
        Define.TV_ROUTE,
        Define.TV_NAME,
        Icons.Filled.Tv,
        Icons.Outlined.Tv
    )
    object SettingScreen: BaseScreen(
        Define.SETTING_ROUTE,
        Define.SETTING_NAME,
        Icons.Default.AccountCircle,
        Icons.Outlined.AccountCircle
    )
    object MovieDetailScreen: BaseScreen(
        Define.MOVIE_DETAIL_ROUTE,
        Define.MOVIE_DETAIL_NAME,
        Icons.Default.Movie,
        Icons.Outlined.Movie
    )
    object TvDetailScreen: BaseScreen(
        Define.TV_DETAIL_ROUTE,
        Define.TV_DETAIL_NAME,
        Icons.Default.Tv,
        Icons.Outlined.Tv
    )
}
