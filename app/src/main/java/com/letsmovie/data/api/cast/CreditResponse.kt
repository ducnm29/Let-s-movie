package com.letsmovie.data.api.cast

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Credit
import com.letsmovie.util.Define.Companion.EMPTY

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
    val order: Int?,
    @SerializedName("birthday")
    val birthDay: String?,
    @SerializedName("deathday")
    val deathDay: String?,
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>?,
)

fun CreditResponse.toModel(): Credit {
    return Credit(
        id = id,
        adult = adult ?: false,
        knownForDepartment = knownForDepartment ?: EMPTY,
        name = name ?: EMPTY,
        originalName = originalName ?: EMPTY,
        profilePath = profilePath ?: EMPTY,
        castId = castId ?: 0,
        creditId = creditId ?: EMPTY,
        character = character ?: EMPTY,
        birthDay = birthDay ?: EMPTY,
        deathDay = deathDay ?: EMPTY,
        biography = biography ?: EMPTY,
        alsoKnownAs = alsoKnownAs ?: listOf()

    )
}
