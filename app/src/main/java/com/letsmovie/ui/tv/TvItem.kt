package com.letsmovie.ui.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Tv
import com.letsmovie.util.Define

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvItem(
    tv: Tv,
    modifier: Modifier = Modifier,
    onTvClick: (tvId: String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp),
        modifier = modifier,
        onClick = {
            onTvClick(tv.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(width = 100.dp, height = 150.dp)
                    .clip(RoundedCornerShape(bottomEnd = 24.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL + tv.imgPoster)
                    .scale(Scale.FIT)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_image),
                error = painterResource(id = R.drawable.no_image_available)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    modifier = Modifier.width(200.dp),
                    text = tv.tvName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 23.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .width(200.dp),
                    text = tv.tvOverview,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}