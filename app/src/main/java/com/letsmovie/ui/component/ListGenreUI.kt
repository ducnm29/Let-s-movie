package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.letsmovie.model.DataGenreResponse
import com.letsmovie.model.Genre
import com.letsmovie.model.Result
import com.letsmovie.ui.genre.GenreUI

@Composable
fun ListGenreUI(
    modifier: Modifier = Modifier,
    listGenreResult: Result<DataGenreResponse>
) {
    when(listGenreResult){
        is Result.Loading -> {

        }
        is Result.Error -> {

        }
        is Result.Success -> {
            Box(
                modifier = modifier
                    .padding(vertical = 16.dp)
                    .height(84.dp)
            ) {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(listGenreResult.data.listGenre) { genre ->
                        GenreUI(genre = genre)
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