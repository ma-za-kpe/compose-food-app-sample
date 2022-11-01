@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)

package com.maku.composefoodorderapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maku.composefoodorderapp.navigation.FoodOrderAppNavHost
import com.maku.composefoodorderapp.navigation.FoodOrderTopLevelNavigation
import com.maku.composefoodorderapp.navigation.TOP_LEVEL_DESTINATIONS
import com.maku.composefoodorderapp.navigation.TopLevelDestination
import com.maku.composefoodorderapp.ui.component.FoodOrderAppBackground
import com.maku.composefoodorderapp.ui.screens.cart.vm.CartViewModel
import com.maku.composefoodorderapp.ui.screens.menu.vm.MenuViewModel
import com.maku.composefoodorderapp.ui.theme.ComposeFoodOrderAppTheme
import timber.log.Timber

@Composable
fun FoodOrderApp(windowSizeClass: WindowSizeClass) {
    ComposeFoodOrderAppTheme {
        val navController = rememberNavController()
        val foodOrderTopLevelNavigation = remember(navController) {
            FoodOrderTopLevelNavigation(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        FoodOrderAppBackground {
            Scaffold(
                modifier = Modifier,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                bottomBar = {
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                        FoodOrderAppBottomBar(
                            onNavigateToTopLevelDestination = foodOrderTopLevelNavigation::navigateTo,
                            currentDestination = currentDestination
                        )
                    }
                }
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ) {
                    if (windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
                        FoodOrderAppNavRail(
                            onNavigateToTopLevelDestination = foodOrderTopLevelNavigation::navigateTo,
                            currentDestination = currentDestination,
                            modifier = Modifier.safeDrawingPadding()
                        )
                    }

                    FoodOrderAppNavHost(
                        windowSizeClass = windowSizeClass,
                        navController = navController,
                        modifier = Modifier
                            .padding(padding)
                            .consumedWindowInsets(padding)
                    )
                }
            }
        }
    }
}

@Composable
private fun FoodOrderAppBottomBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    cartViewModel: CartViewModel = hiltViewModel(),
    menuViewModel: MenuViewModel = hiltViewModel()
) {
    val cartState by cartViewModel.state.observeAsState()
    Log.d("TAG", "FoodOrderAppBottomBar size: ${menuViewModel.size.observeAsState().value}")
//    if(cartState?.items?.size != 0){
//        Log.d("TAG", "FoodOrderAppBottomBar: badgeNumber 1 ${cartState?.items?.size}")
//    } else {
//        Log.d("TAG", "FoodOrderAppBottomBar: badgeNumber 2 ${cartState?.items?.size}")
//    }

    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        NavigationBar(
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                )
            ),
            tonalElevation = 0.dp
        ) {

            TOP_LEVEL_DESTINATIONS.forEach { destination ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToTopLevelDestination(destination) },
                    icon = {
                        if (destination.withBadge) {
                            BadgedBox(
                                badge = {
                                    Badge {
                                        val badgeNumber = cartState?.items?.size
                                        Log.d("TAG", "FoodOrderAppBottomBar: badgeNumber ${cartState?.items?.size}")
                                        Text(
                                            "${badgeNumber}",
                                            modifier = Modifier.semantics {
                                                contentDescription =
                                                    "$badgeNumber new items in cart"
                                            }
                                        )
                                    }
                                }) {
                                Icon(
                                    if (selected) {
                                        destination.selectedIcon
                                    } else {
                                        destination.unselectedIcon
                                    },
                                    contentDescription = null
                                )
                            }
                        } else {
                            Icon(
                                if (selected) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                },
                                contentDescription = null
                            )
                        }
                    },
                    label = { Text(stringResource(destination.iconTextId)) }
                )
            }
        }
    }
}

@Composable
private fun FoodOrderAppNavRail(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        TOP_LEVEL_DESTINATIONS.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}
