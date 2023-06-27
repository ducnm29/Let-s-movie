package com.letsmovie.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.letsmovie.data.Define
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
        Box (
            modifier = Modifier.padding(innerPadding)
        ){
            MyAppNavHost(
                navController = navController,
                startDestination = Define.MOVIE_HOME)
        }
    }
}