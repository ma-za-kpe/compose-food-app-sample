package com.maku.composefoodorderapp.core.cache

import com.maku.composefoodorderapp.core.cache.dao.CartDao
import com.maku.composefoodorderapp.core.cache.dao.MenuDao
import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.cache.model.Menu
import io.reactivex.Flowable
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CacheImpl @Inject constructor(
    private val menuDao: MenuDao,
    private val cartDao: CartDao
) : Cache {

    init {
        initDatabase()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initDatabase() {
        GlobalScope.launch {
            // Prepopulate menu items
            val items = Menu.DEFAULT_MENU_ITEMS.toTypedArray()
            if (menuDao.getMenuItemsSync().isEmpty()) {
                // insert all menu items
                menuDao.insertMenuItems(*items)
            }
        }
    }

    override fun getMenuItems(): Flowable<List<Menu>> {
        return menuDao.getAllMenuItem()
    }

    override suspend fun updateMenuItemAdded(added: Boolean, id: Long) {
        menuDao.updateMenuItemAdded(added, id)
    }

    override fun getCartItems(): Flow<List<Cart>> {
        return cartDao.getAllCartItem()
    }

    override suspend fun insertCartItem(cart: Cart) {
        cartDao.insertCartItem(cart)
    }

    override suspend fun nukeCartTable() {
       cartDao.nukeCartTable()
    }

    override suspend fun deleteCartItemById(id: Long) {
        cartDao.deleteCartItemById(id)
    }

    override suspend fun updateCartItemQty(qty: Int, id: Long) {
        cartDao.updateCartItemQty(qty, id)
    }
}
