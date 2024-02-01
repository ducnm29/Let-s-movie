package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: String,
    val logoPath: String,
    val name: String,
    val originCountry: String
)
