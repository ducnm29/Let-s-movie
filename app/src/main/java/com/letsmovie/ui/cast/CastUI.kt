package com.letsmovie.ui.cast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Credit
import com.letsmovie.util.Define

@Composable
fun CastUI(
    modifier: Modifier = Modifier,
    cast: Credit,
    onCastItemClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.cast_item_size))
            .clickable {
                onCastItemClick(cast.id)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Define.BASE_IMG_URL_ORIGIN.plus(cast.profilePath))
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.loading_image),
            error = painterResource(id = R.drawable.no_image_available),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.cast_image_item_size))
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical0)))
        Text(
            text = cast.name,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_vertical0)))
        Text(
            text = cast.character,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}
