package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.R
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Movie
import com.letsmovie.model.Result

@Composable
fun ListMovieDetailUI(
    modifier: Modifier = Modifier,
    categoryType: String,
    listMovie: List<Movie>,
    onclick: (String) -> Unit,
    onViewMoreClick: () -> Unit
) {

    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.spacer_horizontal1),
            end = dimensionResource(id = R.dimen.spacer_horizontal1)
        )
    ) {
        Text(
            text = categoryType,
            fontWeight = FontWeight.Medium,
            fontSize = dimensionResource(id = R.dimen.category_title).value.sp,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.spacer_vertical2)
            )
        )
        val maxItem = if(listMovie.size > 5) 5 else listMovie.size
        listMovie.subList(0, maxItem).forEach { movie ->
            MovieItemHorizontalUI(
                movie = movie,
                onclick = onclick
            )
        }
        OutlinedButton(
            onClick = onViewMoreClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Text(text = stringResource(id = R.string.view_more))
        }
    }

}