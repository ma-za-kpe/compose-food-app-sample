package com.maku.composefoodorderapp.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.maku.composefoodorderapp.core.cache.model.Menu
import com.maku.composefoodorderapp.R.string.not_adding_to_cart
import com.maku.composefoodorderapp.R.string.adding_to_cart
import com.maku.composefoodorderapp.ui.component.CartButton

@Composable
fun MenuItemList(manu: List<Menu>, modifier: Modifier, onAddClick: (Long, Boolean) -> Unit,) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .heightIn(max = max(560.dp, with(LocalDensity.current) { 560.sp.toDp() }))
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        items(manu) { item ->
//            FoodItem(
//                item = item,
//                modifier = modifier,
//                addedtoCart = "none",
//                onAddClick = { isChecked ->
//                    onAddClick(item.id, isChecked)
//                },
//            )
//        }

        items(items = manu, key = { item -> item.id }) { menuItem ->
            FoodItem(
                item = menuItem,
                modifier = modifier,
                addedtoCart = menuItem.added,
                onAddClick = { isChecked ->
                    onAddClick(menuItem.id, isChecked)
                },
            )
        }
    }
}

@Composable
fun FoodItem(
    item: Menu, modifier:
    Modifier = Modifier,
    addedtoCart: Boolean,
    onAddClick: (Boolean) -> Unit,
) {
    val pickedDescription = if (addedtoCart) {
        stringResource(id = adding_to_cart)
    } else {
        stringResource(id = not_adding_to_cart)
    }
    Column(
        modifier = modifier
            .toggleable(
                value = addedtoCart,
                enabled = true,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onValueChange = { newMenuItemAdded -> onAddClick(newMenuItemAdded) },
            )
            .sizeIn(maxWidth = 48.dp)
            .semantics(mergeDescendants = true) {
                stateDescription = "$pickedDescription ${item.name}"
            }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            val itemImageModifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
            if (item.url.isEmpty()) {
                Icon(
                    modifier = itemImageModifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(4.dp),
                    imageVector = Icons.Filled.Person,
                    contentDescription = null // decorative image
                )
            } else {
                AsyncImage(
                    modifier = itemImageModifier,
                    model = item.url,
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }
            CartButton(
                addedtoCart = addedtoCart,
                backgroundColor = MaterialTheme.colorScheme.surface,
                size = 20.dp,
                iconSize = 14.dp,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
