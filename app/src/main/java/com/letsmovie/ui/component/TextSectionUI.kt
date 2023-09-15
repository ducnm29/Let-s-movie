package com.letsmovie.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.letsmovie.R
import com.letsmovie.util.Define

@Composable
fun TextSectionUI(
    modifier: Modifier = Modifier,
    titleRes: Int,
    body: String,
) {
    var isExpand by rememberSaveable {
        mutableStateOf(false)
    }
    var isShowExpandAction by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = if (body.trim().isEmpty()) stringResource(id = R.string.no_value_data) else body,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.spacer_vertical1))
                .animateContentSize(),
            maxLines = if (isExpand) Define.MAX_LINES else Define.MIN_LINES,
            onTextLayout = {
                // Check collapse condition when number lines of body is equal to Define.MIN_LINES
                val isCollapseStateEllipsized = (it.lineCount == Define.MIN_LINES) &&
                        it.isLineEllipsized(Define.MIN_LINES - 1)
                isShowExpandAction = (it.lineCount > Define.MIN_LINES) || isCollapseStateEllipsized
            },
            overflow = TextOverflow.Ellipsis
        )

        // Show view more icon when number lines of body is bigger than Define.MIN_LINES
        if (isShowExpandAction) {
            Icon(
                imageVector = if (isExpand) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        isExpand = !isExpand
                    }
            )
        }
    }
}