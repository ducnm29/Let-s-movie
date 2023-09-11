package com.letsmovie.ui.component

import androidx.compose.runtime.Composable
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

        }

        is Result.Success -> {
            if(result.data.dataList.isNotEmpty()){
                ListItemUI(
                    //modifier = modifier,
                    listName = categoryName,
                    onClick = onClick,
                    listItem = result.data.dataList
                )
            }
        }

        is Result.Error -> {

        }
    }

}