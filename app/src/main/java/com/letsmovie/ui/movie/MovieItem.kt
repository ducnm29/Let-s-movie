package com.letsmovie.ui.movie

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card (
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.imgUrl)
                .scale(Scale.FIT)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}