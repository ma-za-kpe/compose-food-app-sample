package com.maku.composefoodorderapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maku.composefoodorderapp.ui.screens.cart.CartRoute

object CartDestination : FoodOrderNavigationDestination {
    override val route = "cart_route"
    override val destination = "cart_destination"
}

fun NavGraphBuilder.cartGraph(
) {
    composable(route = CartDestination.route) {
        CartRoute()
    }
}


