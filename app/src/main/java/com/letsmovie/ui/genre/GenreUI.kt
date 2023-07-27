package com.letsmovie.ui.genre

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.R
import com.letsmovie.model.Genre

@Composable
fun GenreUI(
    modifier: Modifier = Modifier,
    genre: Genre
) {
    //val listColors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.)
    Row(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.genre_width))
            .height(dimensionResource(id = R.dimen.genre_height))
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.tertiary),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Category,
            contentDescription = null,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacer_horizontal0))
        )
        Text(
            text = genre.name,
            fontSize = dimensionResource(id = R.dimen.genre_title).value.sp,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}

@Preview
@Composable
fun PreviewGenre() {
    GenreUI(Modifier, Genre("1", "Detective"))
}