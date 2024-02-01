package com.letsmovie.util

import com.letsmovie.model.Movie

class Define {
    companion object {
        /**
         * Route constant for navigation
         */
        const val MOVIE_ROUTE = "movie_route"
        const val FAVOURITE_ROUTE = "favourite_route"
        const val FAVOURITE_HOME = "favourite_home"
        const val SETTING_ROUTE = "setting_route"
        const val SETTING_HOME = "setting_home"
        const val TV_ROUTE = "tv_route"
        const val MOVIE_DETAIL_ROUTE = "movie_detail_route"
        const val MOVIE_HOME = "movie_home"
        const val TV_DETAIL_ROUTE = "tv_detail_route"
        const val TV_HOME = "tv_home"
        const val MOVIE_BY_GENRE_ROUTE = "movie_by_genre_route"
        const val MOVIE_BY_TYPE_ROUTE = "movie_by_type_route"
        const val MOVIE_BY_SEARCH_ROUTE = "movie_by_search_route"

        /**
         *  Const variable
         */
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"
        const val BASE_IMG_URL_ORIGIN = "https://image.tmdb.org/t/p/original/"
        var LANGUAGE_DEFAULT = "EN"
        const val MAX_PAGING_SIZE = 5
        const val PREFETCH_DISTANCE = 2
        const val TOP_RATED_MOVIE = "Top Rated"
        const val UP_COMING_MOVIE = "Up Coming"
        const val MIN_LINES = 3
        const val MAX_LINES = Int.MAX_VALUE

        /**
         * Movie object sample
         */
        val MOVIE_SAMPLE = Movie(
            id = "1",
            movieName = "Test Name",
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
            runtime = 0,
            originalLanguage = "",
            productionCompanies = listOf(),
            status = ""

        )
    }
}