package com.letsmovie.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.letsmovie.R
import com.letsmovie.model.DataListResponse
import com.letsmovie.model.Result
import com.letsmovie.ui.movie.ListItemUI

@Composable
fun <T : Any> ListItemWithData(
    result: Result<DataListResponse<T>>,
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
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
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = stringResource(id = R.string.common_error))
                Spacer(modifier = Modifier.height(8.dp))
                Log.e("ex", result.exception)
            }
        }
    }

}