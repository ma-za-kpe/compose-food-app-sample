package com.maku.composefoodorderapp.ui.screens.cart.usecases

import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.repo.FoodRepo
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class deleteCartItemById @Inject constructor(
    private val foodRepo: FoodRepo
) {
    suspend operator fun invoke(id: Long) = foodRepo.deleteCartItemById(id)
}
