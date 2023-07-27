package com.letsmovie.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
    listMovieResult: Result<DataListResponse<Movie>>,
    onclick: (String) -> Unit
) {
    when (listMovieResult) {
        is Result.Loading -> {

        }

        is Result.Error -> {

        }

        is Result.Success -> {
            Column(
                modifier = modifier.padding(
                    start = dimensionResource(id = R.dimen.spacer_horizontal1).value.dp,
                    end = dimensionResource(id = R.dimen.spacer_horizontal1).value.dp,
                    top = dimensionResource(id = R.dimen.spacer_horizontal3).value.dp
                )
            ) {
                Text(
                    text = categoryType,
                    fontWeight = FontWeight.Medium,
                    fontSize = dimensionResource(id = R.dimen.category_title).value.sp,
                    modifier = Modifier.padding(
                        bottom = dimensionResource(id = R.dimen.spacer_vertical3).value.dp
                    )
                )
                listMovieResult.data.dataList.subList(0, 5).forEach { movie ->
                    MovieItemHorizontalUI(
                        movie = movie,
                        onclick = onclick
                    )
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.spacer_vertical1).value.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = stringResource(id = R.string.view_more))
                }
            }
        }
    }

}