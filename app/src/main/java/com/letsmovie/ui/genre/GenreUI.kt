package com.letsmovie.ui.genre

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.R
import com.letsmovie.model.Genre
import com.letsmovie.ui.theme.LavenderRose
import com.letsmovie.ui.theme.OxfordBlue

@Composable
fun GenreUI(
    modifier: Modifier = Modifier,
    genre: Genre
) {
    val listColors = listOf(LavenderRose, OxfordBlue)
    Card (
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .width(dimensionResource(id = R.dimen.genre_width).value.dp)
            .height(dimensionResource(id = R.dimen.genre_height))

    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 6.dp,
                    top = 6.dp,
                    bottom = 6.dp
                ),
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                imageVector = Icons.Default.Category,
                contentDescription = null
            )
            Text(
                text = genre.name,
                fontSize = dimensionResource(id = R.dimen.genre_title).value.sp,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .padding(start = 6.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewGenre(){
    GenreUI(Modifier,Genre("1","Detective"))
}