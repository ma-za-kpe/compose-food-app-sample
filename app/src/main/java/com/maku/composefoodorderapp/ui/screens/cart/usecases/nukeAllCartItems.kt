package com.maku.composefoodorderapp.ui.screens.cart.usecases

import com.maku.composefoodorderapp.core.repo.FoodRepo
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class nukeAllCartItems @Inject constructor(
    private val foodRepo: FoodRepo
) {
    suspend operator fun invoke() = foodRepo.nukeCartTable()
}
