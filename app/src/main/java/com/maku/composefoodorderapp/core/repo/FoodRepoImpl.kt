package com.maku.composefoodorderapp.core.repo

import com.maku.composefoodorderapp.core.cache.Cache
import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.cache.model.Menu
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepoImpl @Inject constructor(
    private val cache: Cache
): FoodRepo {
    override fun getMenuItems(): Flowable<List<Menu>> {
        return cache.getMenuItems()
    }

    override suspend fun updateMenuItemAdded(added: Boolean, id: Long) {
        cache.updateMenuItemAdded(added, id)
    }

    override fun getCartItems(): Flow<List<Cart>> {
        return cache.getCartItems()
    }

    override suspend fun insertCartItem(cart: Cart) {
        cache.insertCartItem(cart)
    }

    override suspend fun nukeCartTable() {
        cache.nukeCartTable()
    }

    override suspend fun deleteCartItemById(id: Long) {
        cache.deleteCartItemById(id)
    }

    override suspend fun updateCartItemQty(qty: Int, id: Long) {
        cache.updateCartItemQty(qty, id)
    }
}
