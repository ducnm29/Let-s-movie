package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class DataCastResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("cast")
    val listCast: List<Credit>
)
