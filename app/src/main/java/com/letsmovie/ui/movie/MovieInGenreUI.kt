package com.letsmovie.ui.movie

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.ui.component.SearchBarUI
import com.letsmovie.util.Define

@Composable
fun MovieInGenreUI(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel,
    genreId: String,
    onMovieClick: (String) -> Unit
) {
    LaunchedEffect(true){
        movieViewModel.getMovieInGenre(
            Define.LANGUAGE_DEFAULT,
            Define.API_KEY,
            genreId
        )
    }
    val movieResult = movieViewModel.movieInGenre.collectAsState().value
    when (movieResult) {
        is Result.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Error -> {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = stringResource(id = R.string.common_error))
                Spacer(modifier = Modifier.height(8.dp))
                Log.e("ex", movieResult.exception)
            }
        }

        is Result.Success -> {
            BodyMovieInGenreUI(
                modifier = modifier,
                onMovieClick = onMovieClick,
                movieList = movieResult.data.dataList
            )
        }
    }
}

@Composable
fun BodyMovieInGenreUI(
    modifier: Modifier,
    onMovieClick: (String) -> Unit,
    movieList: List<Movie>
) {
    Column(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_vertical2)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_horizontal3)),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(id = R.dimen.spacer_horizontal2),
                vertical = dimensionResource(id = R.dimen.spacer_vertical1)
            )

        ) {
            //Search Bar section
            item(span = { GridItemSpan(5) }) {
                SearchBarUI()
            }
            //List movie section
            items(movieList) { movie ->
                MovieItem(
//                    modifier = Modifier
//                        .width( 140.dp)
//                        .height(180.dp),
                    movie = movie,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}