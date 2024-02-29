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
    val birthDay: String,
    val deathDay: String,
    val biography: String,
    val alsoKnownAs: List<String>,
) {
    companion object {
        val EMPTY = Credit(
            id = "",
            adult = false,
            knownForDepartment = "",
            name = "",
            originalName = "",
            profilePath = "",
            castId = 0,
            creditId = "",
            character = "",
            birthDay = "",
            deathDay = "",
            biography = "",
            alsoKnownAs = listOf()
        )
    }
}
