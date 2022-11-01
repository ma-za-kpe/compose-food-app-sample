package com.maku.composefoodorderapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Scanner
import androidx.compose.material.icons.outlined.ShoppingCartCheckout
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.maku.composefoodorderapp.R.string.menu
import com.maku.composefoodorderapp.R.string.scan
import com.maku.composefoodorderapp.R.string.checkout

/**
 * Routes for the different top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */

/**
 * Models the navigation top level actions in the app.
 */
class FoodOrderTopLevelNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

data class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val withBadge: Boolean
)

val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = MenuDestination.route,
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu,
        iconTextId = menu,
        false
    ),
    TopLevelDestination(
        route = CartDestination.route,
        selectedIcon = Icons.Filled.ShoppingCartCheckout,
        unselectedIcon = Icons.Outlined.ShoppingCartCheckout,
        iconTextId = checkout,
        true
    ),
    TopLevelDestination(
        route = ScanDestination.route,
        selectedIcon = Icons.Filled.Scanner,
        unselectedIcon = Icons.Outlined.Scanner,
        iconTextId = scan,
        false
    )
)

