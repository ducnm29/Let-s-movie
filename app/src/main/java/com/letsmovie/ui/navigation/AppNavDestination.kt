package com.letsmovie.ui.navigation

import androidx.navigation.NamedNavArgument

interface AppNavDestination {
    val route: String
    val listArgument: List<NamedNavArgument>
        get() = emptyList()
}