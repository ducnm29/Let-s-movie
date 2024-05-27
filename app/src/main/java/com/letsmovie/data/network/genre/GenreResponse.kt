package com.letsmovie.data.network.genre

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Genre

data class GenreResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?
)

fun GenreResponse.toModel(): Genre {
    return Genre(
        id = id,
        name = name ?: ""
    )
}
