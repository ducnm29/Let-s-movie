package com.letsmovie.ui.tv

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.model.Movie
import com.letsmovie.model.Tv
import com.letsmovie.util.Define

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvItem(
    tv: Tv,
    modifier: Modifier = Modifier,
    onMovieClick : ()-> Unit
) {
    Card (
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        onClick = onMovieClick
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Define.BASE_IMG_URL+tv.imgPoster)
                .scale(Scale.FIT)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}