package com.maku.composefoodorderapp.ui.screens.menu.usecases

import com.maku.composefoodorderapp.core.repo.FoodRepo
import javax.inject.Inject

class GetMenuItems @Inject constructor(
    private val foodRepo: FoodRepo
) {
    operator fun invoke() = foodRepo.getMenuItems()
        .filter { it.isNotEmpty() }
}
