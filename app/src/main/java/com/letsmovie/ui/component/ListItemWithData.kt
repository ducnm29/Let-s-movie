package com.letsmovie.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Result
import com.letsmovie.ui.movie.ListItemUI

@Composable
fun <T : Any> ListItemWithData(
    result: Result<DataListResponse<T>>,
    modifier: Modifier = Modifier,
    categoryName: String,
    onClick: (itemId: String) -> Unit
) {
    when (result) {
        is Result.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Success -> {
            ListItemUI(
                listName = categoryName,
                onClick = onClick,
                listItem = result.data.dataList
            )
        }

        is Result.Error -> {

        }
    }

}