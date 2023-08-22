package com.letsmovie.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.letsmovie.util.Define

object MovieDestination : AppNavDestination {
    override val route = Define.MOVIE_ROUTE

}

object TvDestination : AppNavDestination {
    override val route = Define.TV_ROUTE
}

object FavoriteDestination : AppNavDestination {
    override val route = Define.FAVOURITE_ROUTE
}

object SettingDestination : AppNavDestination {
    override val route = Define.SETTING_ROUTE
}

object MovieDetailDestination : AppNavDestination {
    const val movieIdArg = "movieId"
    override val route = Define.MOVIE_DETAIL_ROUTE + "/{$movieIdArg}"

    override val listArgument: List<NamedNavArgument>
        get() = listOf(
            navArgument(movieIdArg) { type = NavType.StringType }
        )

    fun createNavRoute(movieId: String): String {
        return Define.MOVIE_DETAIL_ROUTE + "/$movieId"
    }
}

object TvDetailDestination : AppNavDestination {
    const val tvIdArgs = "tvId"
    override val route = Define.TV_DETAIL_ROUTE + "/{$tvIdArgs}"

    override val listArgument: List<NamedNavArgument>
        get() = listOf(
            navArgument(MovieByGenreDestination.genreIDArg) { type = NavType.StringType }
        )

    fun createNavRoute(tvId: String): String{
        return Define.TV_DETAIL_ROUTE + "/$tvId"
    }
}

object MovieByGenreDestination : AppNavDestination {
    const val genreIDArg = "genreId"
    override val route = Define.MOVIE_BY_GENRE_ROUTE + "/{$genreIDArg}"

    override val listArgument: List<NamedNavArgument>
        get() = listOf(
            navArgument(genreIDArg) { type = NavType.StringType }
        )

    fun createNavRoute(genreId: String): String {
        return Define.MOVIE_BY_GENRE_ROUTE + "/$genreId"
    }
}

object MovieByTypeDestination: AppNavDestination{
    const val movieType = "movieType"
    override val route = Define.MOVIE_BY_TYPE_ROUTE + "/{$movieType}"

    override val listArgument: List<NamedNavArgument>
        get() = listOf(
            navArgument(movieType){ type = NavType.StringType}
        )
    fun createNavRoute(movieType: String): String{
        return Define.MOVIE_BY_TYPE_ROUTE + "/$movieType"
    }
}

object MovieBySearchDestination: AppNavDestination{
    override val route = Define.MOVIE_BY_SEARCH_ROUTE
    fun createNavRoute(): String{
        return Define.MOVIE_BY_TYPE_ROUTE
    }
}