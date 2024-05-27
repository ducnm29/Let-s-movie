package com.letsmovie.data.network.movie

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.ProductionCompany

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)

fun ProductionCompanyResponse.toModel() : ProductionCompany {
    return ProductionCompany(
        id = id,
        logoPath = logoPath ?: "",
        name = name ?: "",
        originCountry = originCountry ?: ""
    )
}
