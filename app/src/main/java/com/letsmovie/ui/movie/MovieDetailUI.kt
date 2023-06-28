package com.letsmovie.ui.movie

import android.graphics.Paint.Align
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.model.Movie
import com.letsmovie.model.TagIcon
import com.letsmovie.ui.component.TagIconUI
import com.letsmovie.util.Define

@Composable
fun MovieDetailUI(
    navHostController:NavHostController,
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Box (
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Define.BASE_IMG_URL1+movie.imgBackground)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                alpha = 0.4f,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                IconButton(
                    onClick = {
                              navHostController.popBackStack()
                    },
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 16.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
                Text(
                    text = movie.movieName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 23.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.Sharp.Favorite,
                    contentDescription = null,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        end = 16.dp
                    ),
                    tint = Color.DarkGray
                )
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(240.dp)
                    .height(400.dp)
                    .padding(top = 64.dp)
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(Define.BASE_IMG_URL1+movie.imgPoster)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Row (
                horizontalArrangement = Arrangement.Center
            ){
                TagIconUI(
                    tagIcon = TagIcon(movie.releaseDate,Icons.Default.DateRange)
                )
                TagIconUI(
                    tagIcon = TagIcon(movie.voteAverage.toString(),Icons.Default.Done)
                )
                TagIconUI(
                    tagIcon = TagIcon(movie.mediaType,Icons.Default.Info)
                )
            }
            Text(
                text = "Story line",
                fontWeight = FontWeight.Medium,
                fontSize = 23.sp,
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        start = 16.dp
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = movie.movieOverview,
                fontSize = 18.sp,
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