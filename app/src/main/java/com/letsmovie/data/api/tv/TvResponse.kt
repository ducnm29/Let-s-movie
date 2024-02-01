package com.letsmovie.data.api.tv

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Genre
import com.letsmovie.model.Producer
import com.letsmovie.model.Tv

data class TvResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val tvName: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("poster_path")
    val imgPoster: String?,
    @SerializedName("overview")
    val tvOverview: String?,
    @SerializedName("backdrop_path")
    val imgBackground: String?,
    @SerializedName("genre_ids")
    val genreIdArr: List<Int>?,
    @SerializedName("popularity")
    val popularityPoint: Float?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("created_by")
    val createdBy: List<ProducerResponse>?,
    @SerializedName("genres")
    val genreList: List<Genre>?,
    @SerializedName("languages")
    val languageList: List<String>?,
    @SerializedName("status")
    val status: String?
)

fun TvResponse.toModel(): Tv {
    return Tv(
        id = id,
        tvName = tvName ?: "",
        originalName = originalName ?: "",
        imgPoster = imgPoster ?: "",
        tvOverview = tvOverview ?: "",
        imgBackground = imgBackground ?: "",
        genreIdArr = genreIdArr ?: listOf(),
        popularityPoint = popularityPoint ?: 0f,
        firstAirDate = firstAirDate ?: "",
        voteAverage = voteAverage ?: 0f,
        voteCount = voteCount ?: 0,
        mediaType = mediaType ?: "",
        originCountry = originCountry ?: listOf(),
        createdBy = createdBy?.map { it.toModel() } ?: listOf(),
        genreList = genreList ?: listOf(),
        languageList = languageList ?: listOf(),
        status = status ?: ""
    )
}
