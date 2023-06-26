package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.SearchBarUI

@Composable
fun MovieUI() {
    Column(
        modifier = Modifier.
                verticalScroll(rememberScrollState())
    ) {
        HeaderUserInfoUI()
        SearchBarUI()
        ListMovieUI(
            listMovieName = "Popular"
        )
        ListMovieUI(
            listMovieName = "Trending"
        )
    }
}