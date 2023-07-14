package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.util.Define

@Composable
fun CarouselItem(
    movieItem: Movie,
    modifier: Modifier = Modifier
) {
    Column(

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Define.BASE_IMG_URL_ORIGIN + movieItem.imgBackground)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            placeholder = painterResource(id = R.drawable.loading),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movieItem.movieName,
            fontSize = dimensionResource(id = R.dimen.item_title).value.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))
        RatingBar(
            starNumber = 5,
            rating = movieItem.voteAverage/10 * 5
        )
        Spacer(modifier = Modifier.height(8.dp))
    }


}