package com.letsmovie.data.api.cast

import com.google.gson.annotations.SerializedName
import com.letsmovie.data.api.movie.MovieResponse

data class MovieRelatedResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("cast")
    val listMovieRelated: List<MovieResponse>?
)
