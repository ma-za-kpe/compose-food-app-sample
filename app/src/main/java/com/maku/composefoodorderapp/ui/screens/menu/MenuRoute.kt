@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class
)

package com.maku.composefoodorderapp.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maku.composefoodorderapp.R.string.onboarding_guidance_subtitle
import com.maku.composefoodorderapp.R.string.menu_loading
import com.maku.composefoodorderapp.R.string.top_app_bar_preview_title
import com.maku.composefoodorderapp.R.string.top_app_bar_navigation_button_content_desc
import com.maku.composefoodorderapp.ui.component.FoodOrderAppBackground
import com.maku.composefoodorderapp.ui.component.FoodOrderTopAppBar
import com.maku.composefoodorderapp.ui.component.LoadingWheel
import com.maku.composefoodorderapp.ui.screens.menu.vm.MenuViewModel
import com.maku.composefoodorderapp.ui.theme.FoodTypography
import kotlin.math.floor

@Composable
fun MenuRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    menuViewModel: MenuViewModel = hiltViewModel()
) {
    val menuState = menuViewModel.state.observeAsState()
    MenuScreen(
        windowSizeClass = windowSizeClass,
        modifier = modifier,
        menuState = menuState,
        onMenuItemCheckedChanged = menuViewModel::onMenuItemSelectionEvents
    )
}

@Composable
fun MenuScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    menuState: State<MenuItemsViewState?>,
    onMenuItemCheckedChanged: (Long, Boolean) -> Unit,
    ) {
    FoodOrderAppBackground {
        Scaffold(
            topBar = {
                FoodOrderTopAppBar(
                    titleRes = top_app_bar_preview_title,
                    navigationIcon = Icons.Filled.Search,
                    navigationIconContentDescription = stringResource(
                        id = top_app_bar_navigation_button_content_desc
                    ),
                    actionIcon = Icons.Outlined.AccountCircle,
                    actionIconContentDescription = stringResource(
                        id = top_app_bar_navigation_button_content_desc
                    ),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    )
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            // TODO: Replace with `LazyVerticalGrid` when blocking bugs are fixed:
            //       https://issuetracker.google.com/issues/230514914
            //       https://issuetracker.google.com/issues/231320714
            BoxWithConstraints(
                modifier = modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
            ) {
                val numberOfColumns = when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> 1
                    else -> floor(maxWidth / 300.dp).toInt().coerceAtLeast(1)
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    foodMenuSelectionSection(modifier, menuState.value, onMenuItemCheckedChanged = onMenuItemCheckedChanged)
                    item {
                        Spacer(
                            // TODO: Replace with windowInsetsBottomHeight after
                            //       https://issuetracker.google.com/issues/230383055
                            Modifier.windowInsetsPadding(
                                WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)
                            )
                        )
                    }
                }
            }
        }
    }
}

private fun LazyListScope.foodMenuSelectionSection(
    modifier: Modifier,
    state: MenuItemsViewState?,
    onMenuItemCheckedChanged: (Long, Boolean) -> Unit,
) {
//    item {
//        Text(
//            text = stringResource(onboarding_guidance_title),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
//            textAlign = TextAlign.Center,
//            style = FoodTypography.bodyMedium
//        )
//    }
    item {
        Text(
            text = stringResource(onboarding_guidance_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            style = FoodTypography.titleMedium
        )
    }

    if (state!!.loading) {
        item {
            LoadingWheel(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                contentDesc = stringResource(id = menu_loading),
            )
        }
    } else {
        if (state.items.isNotEmpty()) {
            item {
                MenuItemList(
                    manu = state.items,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onAddClick = onMenuItemCheckedChanged,
                )
            }
        }
    }
}

//private fun handleFailures(failure: Event<Throwable>?) {
//    val unhandledFailure = failure?.getContentIfNotHandled() ?: return
//
//    val fallbackMessage = getString(R.string.an_error_occurred)
//    val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
//        fallbackMessage
//    }
//    else {
//        unhandledFailure.message!!
//    }
//
//    if (snackbarMessage.isNotEmpty()) {
//        Snackbar.make(requireView(), snackbarMessage, Snackbar.LENGTH_SHORT).show()
//    }
//}

