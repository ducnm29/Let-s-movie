package com.letsmovie.model

data class Tv(
    val id: String,
    val tvName: String,
    val originalName: String,
    val imgPoster: String,
    val tvOverview: String,
    val imgBackground: String,
    val genreIdArr: List<Int>,
    val popularityPoint: Float,
    val firstAirDate: String,
    val voteAverage: Float,
    val voteCount: Int,
    val mediaType: String,
    val originCountry: List<String>,
    val createdBy: List<Producer>,
    val genreList: List<Genre>,
    val languageList: List<String>,
    val status: String
)
