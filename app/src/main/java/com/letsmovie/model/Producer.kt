package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class Producer(
    val id: String,
    val creditId: String,
    val name: String,
    val gender: Int,
    val profilePath: String
)
