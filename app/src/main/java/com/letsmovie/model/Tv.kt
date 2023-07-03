package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class Tv(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val tvName: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("poster_path")
    val imgPoster: String,
    @SerializedName("overview")
    val tvOverview: String,
    @SerializedName("backdrop_path")
    val imgBackground: String,
    @SerializedName("genre_ids")
    val genreIdArr: List<Int>,
    @SerializedName("popularity")
    val popularityPoint: Float,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("origin_country")
    val originCountry: List<String>
)
