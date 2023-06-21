package com.letsmovie.ui.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.letsmovie.ui.component.AppNavigation
import com.letsmovie.ui.navigation.BaseScreen
import com.letsmovie.ui.navigation.MyAppNavHost

@Composable
fun MainUI(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppNavigation(
                modifier = modifier,
                navController = navController,
            )
        }
    ) {innerPadding ->
        MyAppNavHost(
            navController = navController,
            startDestination = BaseScreen.MovieScreen.route)
    }
}