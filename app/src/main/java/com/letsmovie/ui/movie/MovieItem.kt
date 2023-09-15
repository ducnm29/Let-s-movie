package com.letsmovie.ui.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.util.Define

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (movieId: String) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onMovieClick(movie.id)
            }
            .width(dimensionResource(id = R.dimen.movie_item_width)),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Define.BASE_IMG_URL + movie.imgPoster)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.loading_image),
            modifier = Modifier
                .fillMaxSize(1f)
                .height(dimensionResource(id = R.dimen.movie_item_height))
                .clip(RoundedCornerShape(10.dp)),
            error = painterResource(id = R.drawable.no_image_available)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.movieName ?: "",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.releaseDate ?: "",
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}