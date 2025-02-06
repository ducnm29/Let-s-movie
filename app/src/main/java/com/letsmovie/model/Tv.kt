package com.letsmovie.model

import com.google.gson.annotations.SerializedName
import com.letsmovie.data.api.tv.ProducerResponse

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
) {
    companion object {
        val EMPTY = Tv(
            id = "",
            tvName = "Empty Name",
            originalName = "",
            imgPoster = "/t2rAdgjSh0WYbXzdOB5zTDqzdCI.jpg",
            tvOverview = "",
            imgBackground = "/t2rAdgjSh0WYbXzdOB5zTDqzdCI.jpg",
            genreIdArr = listOf(),
            popularityPoint = 0f,
            firstAirDate = "",
            voteCount = 0,
            voteAverage = 0f,
            mediaType = "",
            originCountry = listOf(),
            createdBy = listOf(),
            genreList = listOf(),
            languageList = listOf(),
            status = ""
        )
    }
}
