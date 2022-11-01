package com.maku.composefoodorderapp.ui.screens.cart.usecases

import com.maku.composefoodorderapp.core.repo.FoodRepo
import javax.inject.Inject

class UpdateQtyCartItem @Inject constructor(
    private val foodRepo: FoodRepo
) {
    suspend operator fun invoke(qty: Int, id: Long) = foodRepo.updateCartItemQty(qty, id)
}
