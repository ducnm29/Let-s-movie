package com.letsmovie.data.api.cast

import com.google.gson.annotations.SerializedName
import com.letsmovie.data.api.movie.CreditResponse

data class DataCastResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("cast")
    val listCast: List<CreditResponse>
)
