package com.maku.composefoodorderapp.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun FoodOrderAppNavHost(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MenuDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        menuGraph(
            windowSizeClass = windowSizeClass
        )

        cartGraph()
        scanGraph(
//            navigateToTopic = { navController.navigate("${TopicDestination.route}/$it") },
//            navigateToAuthor = { navController.navigate("${AuthorDestination.route}/$it") },
//            nestedGraphs = {
//                topicGraph(onBackClick = { navController.popBackStack() })
//                authorGraph(onBackClick = { navController.popBackStack() })
//            }
        )
    }
}
