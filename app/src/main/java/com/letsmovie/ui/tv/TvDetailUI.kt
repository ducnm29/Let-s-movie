package com.letsmovie.ui.tv


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.letsmovie.R
import com.letsmovie.model.Result
import com.letsmovie.model.TagIcon
import com.letsmovie.model.Tv
import com.letsmovie.ui.component.TagIconUI
import com.letsmovie.util.Define

@Composable
fun TvDetailUI(
    navHostController:NavHostController,
    modifier: Modifier = Modifier,
    tvId: String,
    tvViewModel: TvViewModel
) {
    val tvResult:Result<Tv> = tvViewModel.tvDetailStateFlow.collectAsState().value
    LaunchedEffect(true){
        tvViewModel.getTvDetail(
            tvId = tvId,
            language = "vi",
            apiKey = Define.API_KEY
        )
    }
    when(tvResult){
        is Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }

        }
        is Result.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = stringResource(id = R.string.common_error))
            }

        }

        is Result.Success -> {
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
                            .data(Define.BASE_IMG_URL_ORIGIN + tvResult.data.imgBackground)
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
                            text = tvResult.data.tvName,
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
                                .data(Define.BASE_IMG_URL_ORIGIN + tvResult.data.imgPoster)
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
                            tagIcon = TagIcon(
                                tagName = tvResult.data.firstAirDate,
                                tagIconImageVector = Icons.Default.DateRange)
                        )
                        TagIconUI(
                            tagIcon = TagIcon(
                                tagName = tvResult.data.voteAverage.toString(),
                                tagIconImageVector = Icons.Default.StarRate)
                        )
                        TagIconUI(
                            tagIcon = TagIcon(
                                tagName = tvResult.data.voteAverage.toString(),
                                tagIconImageVector = Icons.Default.AccessTime)
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
                        text = tvResult.data.tvOverview,
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
    }
}