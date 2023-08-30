package com.letsmovie.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.letsmovie.R

@Composable
fun SearchBarInDetailUI(
    modifier: Modifier = Modifier,
    searchKeywordValue: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onCLearClick: () -> Unit,
    focusRequester: FocusRequester = FocusRequester()
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            )
    ) {
        TextField(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = searchKeywordValue,
            onValueChange = onValueChange,
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onBackClick)
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.search_title))
            },
            trailingIcon = {
                if(searchKeywordValue == ""){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = onCLearClick)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = onCLearClick)
                    )
                }
            }

        )
    }
}