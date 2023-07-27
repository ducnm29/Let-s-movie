package com.letsmovie.ui.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                modifier = Modifier.size(72.dp)
            )
        }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_horizontal1)))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = movie.movieName,
                fontSize = dimensionResource(id = R.dimen.item_title).value.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.spacer_vertical0).value.dp)
            )
            Text(
                text = movie.releaseDate,
                fontSize = dimensionResource(id = R.dimen.sub_item_title).value.sp,
                fontWeight = FontWeight.Normal
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