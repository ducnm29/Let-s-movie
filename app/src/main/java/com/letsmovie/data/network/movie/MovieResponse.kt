package com.letsmovie.data.network.movie

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Genre
import com.letsmovie.model.Movie

data class MovieResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val movieName: String?,
    @SerializedName("poster_path")
    val imgPoster: String?,
    @SerializedName("overview")
    val movieOverview: String?,
    @SerializedName("backdrop_path")
    val imgBackground: String?,
    @SerializedName("genre_ids")
    val genreIdArr: List<Int>?,
    @SerializedName("popularity")
    val popularityPoint: Float?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("genres")
    val genreList: List<Genre>?,
    @SerializedName("homepage")
    val homePage: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>?,
    @SerializedName("status")
    val status: String?
)

fun MovieResponse.toModel(): Movie {
    return Movie(
        id = id,
        movieName = movieName ?: "",
        imgPoster = imgPoster ?: "",
        movieOverview = movieOverview ?: "",
        imgBackground = imgBackground ?: "",
        genreIdArr = genreIdArr ?: listOf(),
        popularityPoint = popularityPoint ?: 0f,
        releaseDate = releaseDate ?: "",
        voteAverage = voteAverage ?: 0f,
        voteCount = voteCount ?: 0,
        mediaType = mediaType ?: "",
        genreList = genreList ?: listOf(),
        homePage = homePage ?: "",
        runtime = runtime ?: 0,
        originalLanguage = originalLanguage ?: "",
        productionCompanies = productionCompanies?.map { it.toModel() } ?: listOf(),
        status = status ?: ""
    )
}
