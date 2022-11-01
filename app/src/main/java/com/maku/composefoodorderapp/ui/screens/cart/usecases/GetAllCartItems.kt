package com.maku.composefoodorderapp.ui.screens.cart.usecases

import com.maku.composefoodorderapp.core.repo.FoodRepo
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetAllCartItems @Inject constructor(
    private val foodRepo: FoodRepo
) {
    operator fun invoke() = foodRepo.getCartItems()
        .filter { it.isNotEmpty() }
}
