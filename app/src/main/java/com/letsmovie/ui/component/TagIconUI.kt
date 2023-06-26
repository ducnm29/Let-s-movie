package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.letsmovie.model.TagIcon

@Composable
fun TagIconUI(
    modifier: Modifier = Modifier,
    tagIcon: TagIcon
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            imageVector = tagIcon.tagIconImageVector,
            contentDescription = null,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 8.dp,
                    end = 8.dp
                )
        )
        Text(
            text = tagIcon.tagName,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                )
        )
    }
}