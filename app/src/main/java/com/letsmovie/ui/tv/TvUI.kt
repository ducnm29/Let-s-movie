package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.ui.movie.ListMovieUI

@Composable
fun TvUI() {
    Column(
        modifier = Modifier.
        verticalScroll(rememberScrollState())
    ) {
        HeaderUserInfoUI()
        SearchBarUI()
        ListMovieUI(
            listMovieName = "VTV digital"
        )
        ListMovieUI(
            listMovieName = "Trending"
        )
    }
}