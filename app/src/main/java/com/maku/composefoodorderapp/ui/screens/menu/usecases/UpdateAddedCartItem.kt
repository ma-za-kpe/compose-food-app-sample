package com.maku.composefoodorderapp.ui.screens.menu.usecases

import com.maku.composefoodorderapp.core.repo.FoodRepo
import javax.inject.Inject

class UpdateAddedCartItem @Inject constructor(
    private val foodRepo: FoodRepo
) {
    suspend operator fun invoke(added: Boolean, id: Long) = foodRepo.updateMenuItemAdded(added, id)
}
