package com.letsmovie.ui.component

import android.widget.RatingBar
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RatingBar(
    starNumber: Int,
    rating: Float,
    modifier: Modifier = Modifier
) {
    val halfStar = rating-rating.toInt() != 0f
    Row (
        modifier = modifier
    ){
        repeat(rating.toInt()){
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null)
        }
        if(halfStar){
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null)
        }
        repeat(starNumber - rating.toInt()){
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null)
        }
    }
}