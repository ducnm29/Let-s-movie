package com.letsmovie.ui.main

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.letsmovie.R
import com.letsmovie.ui.component.AppNavigation
import com.letsmovie.ui.navigation.MyAppNavHost
import com.letsmovie.ui.navigation.TopLevelDestination
import com.letsmovie.util.Define

@Composable
fun MainUI(
    modifier: Modifier = Modifier,
    activity: Activity
) {
    val navController = rememberNavController()
    val destination = navController.currentBackStackEntryAsState().value?.destination
    val bottomBarVisibleState = rememberSaveable {
        mutableStateOf(true)
    }
    bottomBarVisibleState.value = TopLevelDestination.values().map { it.startDestination }.any {
        it == destination?.route
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
        Column(
            modifier = Modifier.padding(
                end = innerPadding.calculateRightPadding(LocalLayoutDirection.current),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            MyAppNavHost(
                navController = navController,
                startDestination = Define.MOVIE_HOME,
                activity = activity
            )
        }
    }
}