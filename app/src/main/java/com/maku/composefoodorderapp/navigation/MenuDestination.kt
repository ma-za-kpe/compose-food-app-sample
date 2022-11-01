package com.maku.composefoodorderapp.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maku.composefoodorderapp.ui.screens.menu.MenuRoute

object MenuDestination : FoodOrderNavigationDestination {
    override val route = "menu_route"
    override val destination = "menu_destination"
}

fun NavGraphBuilder.menuGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = MenuDestination.route) {
        MenuRoute(windowSizeClass)
    }
}
