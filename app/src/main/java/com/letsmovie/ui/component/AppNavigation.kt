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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.letsmovie.ui.navigation.TopLevelDestination

@Composable
fun AppNavigation(
    modifier: Modifier,
    navDestination: NavDestination?,
    onCLick: (TopLevelDestination) -> Unit,
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
            TopLevelDestination.values().forEach { screen ->
                val selected = navDestination?.hierarchy?.any { destination ->
                    destination.route == screen.route
                } == true

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onCLick(screen)
                    },
                    icon = {
                        if (selected) {
                            Icon(
                                imageVector = screen.selectedIcon,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = screen.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(text = stringResource(id = screen.label))
                    }
                )
            }
        }
    }
}