package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.model.Movie

@Composable
fun MovieItem(
    movie: Movie
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Card (
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(16.dp)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.imgUrl)
                        .scale(Scale.FIT)
                        .build(),
                    contentDescription = null,
                    )

                Spacer(modifier = Modifier.size(16.dp))

                Text(text = movie.movieName)
            }
        }
    }
}