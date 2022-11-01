package com.maku.composefoodorderapp.core.cache

import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.cache.model.Menu
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface Cache {
    fun getMenuItems(): Flowable<List<Menu>>

    suspend fun updateMenuItemAdded(added: Boolean, id: Long)

    fun getCartItems(): Flow<List<Cart>>

    suspend fun insertCartItem(cart: Cart)

    suspend fun nukeCartTable()

    suspend fun deleteCartItemById(id: Long)

    suspend fun updateCartItemQty(qty: Int, id: Long)
}
