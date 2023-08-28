package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.letsmovie.R
import com.letsmovie.model.DataGenreResponse
import com.letsmovie.model.Result
import com.letsmovie.ui.genre.GenreUI

@Composable
fun ListGenreUI(
    modifier: Modifier = Modifier,
    listGenreResult: Result<DataGenreResponse>,
    onGenreClick: (String, String) -> Unit,
) {
    when (listGenreResult) {
        is Result.Loading -> {

        }

        is Result.Error -> {

        }

        is Result.Success -> {
            Box(
                modifier = modifier
                    .padding(top = dimensionResource(id = R.dimen.spacer_vertical3))
                    .height(96.dp)
            ) {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.spacer_horizontal1))
                ) {
                    items(listGenreResult.data.listGenre) { genre ->
                        GenreUI(
                            genre = genre,
                            onGenreClick = onGenreClick
                        )
                    }
                }
            }
        }

    }
}

//@Preview
//@Composable
//fun ListGenrePreview() {
//    ListGenreUI(
//        Modifier, listOf(
//            Genre("1", "Detective"),
//            Genre("1", "Romantic"),
//            Genre("1", "Fiction"),
//            Genre("1", "Cartoon"),
//            Genre("1", "Documentary")
//        )
//    )
//}