package com.letsmovie.util

import com.letsmovie.model.Movie
import com.letsmovie.ui.navigation.BaseScreen

class Define {
    companion object {
        /**
         * Route constant for navigation
         */
        const val MOVIE_ROUTE = "movie_route"
        const val FAVOURITE_ROUTE = "favourite_route"
        const val SETTING_ROUTE = "setting_route"
        const val TV_ROUTE = "tv_route"
        const val MOVIE_DETAIL_ROUTE = "movie_detail_route"
        const val MOVIE_HOME = "movie_home"
        const val TV_DETAIL_ROUTE = "tv_detail_route"
        const val TV_HOME = "tv_home"
        const val MOVIE_IN_GENRE_ROUTE = "movie_in_genre_route"

        /**
         * Name constant for navigation
         */
        const val MOVIE_NAME = "Movie"
        const val FAVOURITE_NAME = "Favourite"
        const val SETTING_NAME = "Setting"
        const val TV_NAME = "Tv"
        const val MOVIE_DETAIL_NAME = "Movie Detail"
        const val TV_DETAIL_NAME = "Tv Detail"
        const val MOVIE_IN_GENRE_NAME = "Movie In Genre"

        /**
         * List navigation view
         */
        val LIST_SCREEN = listOf(
            BaseScreen.MovieScreen, BaseScreen.TvScreen,
            BaseScreen.FavouriteScreen, BaseScreen.SettingScreen
        )
        val LIST_HOME_SCREEN = listOf(
            BaseScreen.MovieHomeScreen, BaseScreen.TvHomeScreen,
            BaseScreen.FavouriteScreen, BaseScreen.SettingScreen
        )

        /**
         *  Const variable
         */
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"
        const val BASE_IMG_URL_ORIGIN = "https://image.tmdb.org/t/p/original/"
        const val API_KEY = "f39c7e6b303df4c5106ab18f41b95c54"
        const val LANGUAGE_DEFAULT = "en"
        const val HEADER_HELLO = "Hello Duc NM ahyhy"
        const val HEADER_1 = "It's movie time ahyhy!"
        const val ERROR_TAG = "error_tag"

        /**
         * Movie object sample
         */
        val MOVIE_SAMPLE = Movie(
            id = "1",
            movieName = "",
            imgPoster = "/gPbM0MK8CP8A174rmUwGsADNYKD.jpg",
            movieOverview = "",
            imgBackground = "",
            genreIdArr = listOf(),
            popularityPoint = 0f,
            releaseDate = "",
            voteAverage = 0f,
            voteCount = 0,
            mediaType = "",
            genreList = listOf(),
            homePage = "",
            runtime = 0

        )
    }
}