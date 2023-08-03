package com.letsmovie.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.letsmovie.ui.component.AppNavigation
import com.letsmovie.ui.navigation.MyAppNavHost
import com.letsmovie.util.Define

@Composable
fun MainUI(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppNavigation(
                modifier = modifier,
                navDestination = navController.currentBackStackEntryAsState().value?.destination,
                onCLick = { baseScreen ->
                    navController.navigate(baseScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
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