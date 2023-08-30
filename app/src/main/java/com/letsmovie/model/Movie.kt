package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class Movie(
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
    val runtime: Int?
)
