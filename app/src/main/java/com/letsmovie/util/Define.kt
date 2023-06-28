package com.letsmovie.util

import com.letsmovie.ui.navigation.BaseScreen

class Define {
    companion object{
        /**
         * Route constant for navigation
         */
        const val MOVIE_ROUTE = "movie_route"
        const val FAVOURITE_ROUTE = "favourite_route"
        const val SETTING_ROUTE = "setting_route"
        const val TV_ROUTE = "tv_route"
        const val MOVIE_DETAIL_ROUTE = "movie_detail_route"
        const val MOVIE_HOME = "movie_home"

        /**
         * Name constant for navigation
         */
        const val MOVIE_NAME = "movie_route"
        const val FAVOURITE_NAME = "favourite_route"
        const val SETTING_NAME = "setting_route"
        const val TV_NAME = "tv_route"
        const val MOVIE_DETAIL_NAME = "movie_detail_name"
        /**
         * List navigation view
         */
         val LIST_SCREEN = listOf(BaseScreen.MovieScreen, BaseScreen.TvScreen,
            BaseScreen.FavouriteScreen, BaseScreen.SettingScreen)

        /**
         *  Const variable
         */
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"
        const val BASE_IMG_URL1 = "https://image.tmdb.org/t/p/original/"
        const val API_KEY = "f39c7e6b303df4c5106ab18f41b95c54"
        const val HEADER_HELLO = "Hello Duc NM ahyhy"
        const val HEADER_1 = "It's movie time ahyhy!"

    }
}