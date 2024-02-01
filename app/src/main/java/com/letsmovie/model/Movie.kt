package com.letsmovie.model

data class Movie(
    val id: String,
    val movieName: String,
    val imgPoster: String,
    val movieOverview: String,
    val imgBackground: String,
    val genreIdArr: List<Int>,
    val popularityPoint: Float,
    val releaseDate: String,
    val voteAverage: Float,
    val voteCount: Int,
    val mediaType: String,
    val genreList: List<Genre>,
    val homePage: String,
    val runtime: Int,
    val originalLanguage: String,
    val productionCompanies: List<ProductionCompany>,
    val status: String,
) {
    companion object {
        val EMPTY = Movie(
            id = "",
            movieName = "",
            imgPoster = "",
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
