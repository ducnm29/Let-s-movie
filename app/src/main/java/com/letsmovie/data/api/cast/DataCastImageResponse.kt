package com.letsmovie.data.api.cast

import com.google.gson.annotations.SerializedName

data class DataCastImageResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("profiles")
    val listCastImage: List<CastImageResponse>?
)
