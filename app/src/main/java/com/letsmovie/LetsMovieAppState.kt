package com.letsmovie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.letsmovie.ui.navigation.TopLevelDestination

class LetsMovieAppState(
    val navController: NavHostController
) {
    private val navBackStackEntry: NavBackStackEntry?
        @Composable get() = navController.currentBackStackEntryAsState().value
    val currentDestination: NavDestination?
        @Composable get() = navBackStackEntry?.destination

    /**
     * Navigate to TopLevelDestination screens which connected with bottom navigation.
     * In these screens, state is saved and can be restored after navigating.
     * Each of them will be at most one copy in backstack.
     * @param screen TopLevelDestination connected with bottom navigation
     */
    fun navigateBottomNavScreen(screen: TopLevelDestination) {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Navigate to normal screen. Each of these can have multiple copy in backstack
     * @param route Route of destination screen
     */
    fun navigateScreen(route: String) {
        navController.navigate(route = route)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): LetsMovieAppState = remember(navController) {
    LetsMovieAppState(navController = navController)
}
