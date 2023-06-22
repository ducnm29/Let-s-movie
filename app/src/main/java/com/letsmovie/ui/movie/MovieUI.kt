package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.letsmovie.model.Movie
import com.letsmovie.ui.component.HeaderUserInfoUI
import com.letsmovie.ui.component.SearchBarUI

@Composable
fun MovieUI() {
    val movie = Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/237/550/860")
//    Text(text = "Movie UI")
//    MovieItem(
//        movie = movie,
//        modifier = Modifier.size(width = 160.dp, height = 250.dp)
//    )
    Column() {
        HeaderUserInfoUI()
        SearchBarUI()
    }
}