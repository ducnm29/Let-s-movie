package com.letsmovie.ui.movie

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.letsmovie.model.Movie

@Composable
fun MovieUI() {
    Text(text = "Movie UI")
    MovieItem(movie = Movie("1","Fast and mad", "Duc nm", "https://picsum.photos/id/237/300/400"))
}