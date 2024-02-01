package com.letsmovie.model


data class Credit(
    val id: String,
    val adult: Boolean,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val profilePath: String,
    val castId: Int,
    val creditId: String,
    val character: String,
)
