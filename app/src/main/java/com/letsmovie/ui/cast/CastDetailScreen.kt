package com.letsmovie.ui.cast

import android.widget.ImageView.ScaleType
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.ui.component.ListMovie
import com.letsmovie.util.Define

@Composable
fun CastDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Profile image section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + "/cnqwv5Uz3UW5f086IWbQKr3ksJr.jpg")
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )
            AsyncImage(
                modifier = Modifier
                    .padding(
                        top = 210.dp,
                        start = 35.dp
                    )
                    .size(150.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + "/cnqwv5Uz3UW5f086IWbQKr3ksJr.jpg")
                    .scale(Scale.FIT)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )
        }

        ListMovie(
            listMovieName = "Movie",
            onClick = {},
            listMovie = listOf()
        )
    }
}


@Preview
@Composable
fun PreviewCastDetail() {
    CastDetailScreen()
}
