package com.maku.composefoodorderapp.ui.screens.cart.usecases

import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.repo.FoodRepo
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class insertCartItem @Inject constructor(
    private val foodRepo: FoodRepo
) {
    suspend operator fun invoke(cart: Cart) = foodRepo.insertCartItem(cart)
}
