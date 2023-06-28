package com.letsmovie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letsmovie.R
import com.letsmovie.util.Define

@Composable
fun HeaderUserInfoUI(
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(156.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ){
            Text(
                text = Define.HEADER_HELLO,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = Define.HEADER_1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
        }
        Image(
            painter = painterResource(id = R.drawable.pepe),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .padding(end = 16.dp),
        )
    }
}