package com.letsmovie.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.letsmovie.ui.component.AppNavigation
import com.letsmovie.ui.navigation.MyAppNavHost
import com.letsmovie.util.Define
import com.letsmovie.util.Define.Companion.LIST_SCREEN

@Composable
fun MainUI(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val destination = navController.currentBackStackEntryAsState().value?.destination
    val bottomBarVisibleState = rememberSaveable {
        mutableStateOf(true)
    }
    bottomBarVisibleState.value = LIST_SCREEN.any {
        it.route == destination?.route
    }
    Scaffold(
        bottomBar = {
            AppNavigation(
                modifier = modifier,
                navDestination = destination,
                onCLick = { screen ->
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                visibleState = bottomBarVisibleState
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            MyAppNavHost(
                navController = navController,
                startDestination = Define.MOVIE_HOME
            )
        }
    }
}