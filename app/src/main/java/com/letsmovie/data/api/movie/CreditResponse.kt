package com.letsmovie.data.api.movie

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Credit

data class CreditResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("popularity")
    val popularity: Float?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("cast_id")
    val castId: Int?,
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("order")
    val order: Int?
)

fun CreditResponse.toModel(): Credit {
    return Credit(
        id = id,
        adult = adult ?: false,
        knownForDepartment = knownForDepartment ?: "",
        name = name ?: "",
        originalName = originalName ?: "",
        profilePath = profilePath ?: "",
        castId = castId ?: 0,
        creditId = creditId ?: "",
        character = character ?: ""
    )
}
