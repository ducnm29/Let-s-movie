package com.letsmovie.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.letsmovie.ui.navigation.BaseScreen
import com.letsmovie.util.Define.Companion.LIST_SCREEN

@Composable
fun AppNavigation(
    modifier: Modifier,
    navDestination: NavDestination?,
    onCLick: (BaseScreen) -> Unit,
    visibleState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = visibleState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = containerColor
        ) {

            LIST_SCREEN.forEach { baseScreen ->
                val selected = navDestination?.hierarchy?.any { destination ->
                    destination.route == baseScreen.route
                } == true

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onCLick(baseScreen)
                    },
                    icon = {
                        if (selected) {
                            Icon(
                                imageVector = baseScreen.iconSelected,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = baseScreen.iconNormal,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(text = baseScreen.name)
                    }
                )
            }
        }
    }
}