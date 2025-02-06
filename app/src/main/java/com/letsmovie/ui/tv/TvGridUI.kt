package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Tv
import com.letsmovie.util.Define

@Composable
fun TvGridUI(modifier: Modifier = Modifier, tv: Tv) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL + tv.imgBackground)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )

            Text(
                modifier = Modifier
                    .width(200.dp)
                    .padding(start = 10.dp, end = 10.dp),
                text = tv.tvName,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .width(200.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 6.dp),
                text = tv.firstAirDate,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun previewTvGridUI(modifier: Modifier = Modifier) {
    TvGridUI(tv = Tv.EMPTY)
}