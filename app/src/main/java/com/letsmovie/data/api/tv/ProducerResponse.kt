package com.letsmovie.data.api.tv

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.Producer

data class ProducerResponse(
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

fun ProducerResponse.toModel() : Producer {
    return Producer(
        id = id,
        creditId = creditId ?: "",
        name = name ?: "",
        gender = gender ?: 0,
        profilePath = profilePath ?: ""
    )
}
