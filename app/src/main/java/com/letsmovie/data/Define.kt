package com.letsmovie.data

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
         *
         */
        const val HEADER_HELLO = "Hello Duc NM ahyhy"
        const val HEADER_1 = "It's movie time ahyhy!"
    }
}