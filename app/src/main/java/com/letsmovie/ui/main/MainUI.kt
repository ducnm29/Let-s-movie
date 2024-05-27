package com.letsmovie.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import com.letsmovie.LetsMovieAppState
import com.letsmovie.ui.component.AppNavigation
import com.letsmovie.ui.navigation.LoginDestination
import com.letsmovie.ui.navigation.MyAppNavHost
import com.letsmovie.ui.navigation.TopLevelDestination

@Composable
fun MainUI(
    modifier: Modifier = Modifier,
    appState: LetsMovieAppState
) {
    val bottomBarVisibleState = rememberSaveable {
        mutableStateOf(true)
    }
    bottomBarVisibleState.value = TopLevelDestination.values().map { it.startDestination }.any {
        it == appState.currentDestination?.route
    }
    Scaffold(
        bottomBar = {
            AppNavigation(
                modifier = modifier,
                navDestination = appState.currentDestination,
                onCLick = { screen ->
                    appState.navigateBottomNavScreen(screen = screen)
                },
                visibleState = bottomBarVisibleState
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                end = innerPadding.calculateRightPadding(LocalLayoutDirection.current),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            MyAppNavHost(
                navController = appState.navController,
                startDestination = LoginDestination.route,
                onClickBack = {
                    appState.popBackStack()
                },
                onNavigateScreen = { route ->
                    appState.navigateScreen(route)
                }
            )
        }
    }
}