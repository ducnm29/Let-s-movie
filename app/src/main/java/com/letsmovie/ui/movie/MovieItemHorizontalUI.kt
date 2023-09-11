package com.letsmovie.ui.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.util.Define

@Composable
fun MovieItemHorizontalUI(
    modifier: Modifier = Modifier,
    movie: Movie,
    onclick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.spacer_vertical2))
            .clickable {
                onclick(movie.id)
            }
    ) {
        Card(
            shape = RoundedCornerShape(10.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL + movie.imgPoster)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(dimensionResource(id = R.dimen.movie_item_horizontal_size)),
                error = painterResource(id = R.drawable.no_image_available)
            )
        }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_horizontal1)))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = movie.movieName ?: "",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.spacer_vertical0).value.dp)
            )
            Text(
                text = movie.releaseDate ?: "",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.spacer_vertical1).value.dp)
            )

        }
    }
}

@Preview
@Composable
fun MovieItemHorizontalPreview() {
    MovieItemHorizontalUI(
        movie = Define.MOVIE_SAMPLE,
        onclick = {}
    )
}