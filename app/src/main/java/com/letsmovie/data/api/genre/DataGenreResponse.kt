package com.letsmovie.data.api.genre

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Genre

data class DataGenreResponse(
    @SerializedName("genres")
    val listGenre: List<GenreResponse>
)