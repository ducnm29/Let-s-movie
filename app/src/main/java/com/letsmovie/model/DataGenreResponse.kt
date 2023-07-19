package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class DataGenreResponse(
    @SerializedName("genres")
    val listGenre: List<Genre>
)