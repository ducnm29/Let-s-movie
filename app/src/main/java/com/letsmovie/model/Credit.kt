package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("id")
    val id: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("order")
    val order: Int
)
