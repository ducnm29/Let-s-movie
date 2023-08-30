package com.letsmovie.ui.movie.moviedetail


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Movie
import com.letsmovie.model.Result
import com.letsmovie.model.TagIcon
import com.letsmovie.ui.component.TagIconUI
import com.letsmovie.util.Define

@Composable
fun MovieDetailUI(
    modifier: Modifier = Modifier,
    movieDetailViewModel: MovieDetailViewModel,
    onClickBack: () -> Unit
) {
    val movieResult: Result<Movie> = movieDetailViewModel.movieDetail.collectAsState().value

    when (movieResult) {
        is Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        is Result.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.common_error))
            }

        }

        is Result.Success -> {
            MovieDetailBodyUI(
                modifier = modifier,
                movieResult = movieResult,
                onClickBack = onClickBack
            )
        }
    }
}

@Composable
fun MovieDetailBodyUI(
    modifier: Modifier = Modifier,
    movieResult: Result.Success<Movie>,
    onClickBack: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL_ORIGIN + movieResult.data.imgBackground)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                alpha = 0.4f,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                IconButton(
                    onClick = onClickBack,
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 16.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
                Text(
                    text = movieResult.data.movieName ?: "",
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.padding(
                        top = 12.dp,
                        end = 16.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Favorite,
                        contentDescription = null,
                    )
                }
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(360.dp)
                    .padding(top = 12.dp)
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(Define.BASE_IMG_URL_ORIGIN + movieResult.data.imgPoster)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                TagIconUI(
                    tagIcon = TagIcon(
                        tagName = movieResult.data.releaseDate ?: "",
                        tagIconImageVector = Icons.Default.DateRange
                    )
                )
                TagIconUI(
                    tagIcon = TagIcon(
                        tagName = movieResult.data.voteAverage.toString(),
                        tagIconImageVector = Icons.Default.StarRate
                    )
                )
                TagIconUI(
                    tagIcon = TagIcon(
                        tagName = movieResult.data.runtime.toString(),
                        tagIconImageVector = Icons.Default.AccessTime
                    )
                )
            }
            Text(
                text = "Story line",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        start = 16.dp
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = movieResult.data.movieOverview ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 32.dp
                    )
            )

        }
    }
}