package com.letsmovie.data.api.cast

import com.google.gson.annotations.SerializedName

data class DataCreditResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("cast")
    val listCast: List<CreditResponse>
)
