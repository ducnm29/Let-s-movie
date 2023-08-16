package com.letsmovie.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.letsmovie.R
import com.letsmovie.util.Define

enum class TopLevelDestination(
    override val route: String,
    val startDestination: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val label: Int
) : AppNavDestination {
    MOVIE(
        route = Define.MOVIE_HOME,
        startDestination = MovieDestination.route,
        selectedIcon = Icons.Default.Movie,
        unselectedIcon = Icons.Outlined.Movie,
        label = R.string.movie_label
    ),
    TV(
        route = Define.TV_HOME,
        startDestination = TvDestination.route,
        selectedIcon = Icons.Default.Tv,
        unselectedIcon = Icons.Outlined.Tv,
        label = R.string.tv_label
    ),
    FAVOURITE(
        route = Define.FAVOURITE_HOME,
        startDestination = FavoriteDestination.route,
        selectedIcon = Icons.Default.HeartBroken,
        unselectedIcon = Icons.Outlined.Favorite,
        label = R.string.fav_label
    ),
    SETTING(
        route = Define.SETTING_HOME,
        startDestination = SettingDestination.route,
        selectedIcon = Icons.Default.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        label = R.string.setting_label
    )
}
