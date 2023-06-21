package com.letsmovie.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.letsmovie.data.Define.Companion.LIST_SCREEN

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavController
) {
    NavigationBar(
        modifier = Modifier,
        containerColor = containerColor
    ) {
        val selectedItem = rememberSaveable{
            mutableStateOf(0)
        }
        LIST_SCREEN.forEachIndexed { index, baseScreen ->
            NavigationBarItem(
                selected = index == selectedItem.value,
                onClick = {
                    selectedItem.value = index
                    navController.navigate(baseScreen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = baseScreen.icon,
                        contentDescription = null)
                }
            )
        }
    }
}