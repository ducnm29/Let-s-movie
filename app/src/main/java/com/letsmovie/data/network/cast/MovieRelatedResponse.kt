package com.letsmovie.data.network.cast

import com.google.gson.annotations.SerializedName
import com.letsmovie.data.network.movie.MovieResponse

data class MovieRelatedResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("cast")
    val listMovieRelated: List<MovieResponse>?
)
