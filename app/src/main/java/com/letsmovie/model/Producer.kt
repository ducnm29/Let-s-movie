package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class Producer(
    @SerializedName("id")
    val id: String,
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("profile_path")
    val profilePath: String?
)
