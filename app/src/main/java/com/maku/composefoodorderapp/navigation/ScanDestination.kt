package com.maku.composefoodorderapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maku.composefoodorderapp.ui.screens.scan.ScanRoute

object ScanDestination : FoodOrderNavigationDestination {
    override val route = "scan_route"
    override val destination = "scan_destination"
}

fun NavGraphBuilder.scanGraph() {
    composable(route = ScanDestination.route) {
        ScanRoute()
    }
}
