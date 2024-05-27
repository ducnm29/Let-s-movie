package com.letsmovie.data.network.genre

import com.google.gson.annotations.SerializedName

data class DataGenreResponse(
    @SerializedName("genres")
    val listGenre: List<GenreResponse>
)