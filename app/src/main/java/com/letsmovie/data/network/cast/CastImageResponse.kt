package com.letsmovie.data.network.cast

import com.google.gson.annotations.SerializedName
import com.letsmovie.model.CastImage

data class CastImageResponse(
    @SerializedName("aspect_ratio")
    val aspectRatio: String?,
    @SerializedName("file_path")
    val filePath: String?
)

fun CastImageResponse.toModel(): CastImage{
    return CastImage(
        aspectRatio = aspectRatio ?: "0.0",
        filePath = filePath ?: ""
    )
}
