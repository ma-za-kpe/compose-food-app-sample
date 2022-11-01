package com.maku.composefoodorderapp.core.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maku.composefoodorderapp.core.cache.dao.CartDao
import com.maku.composefoodorderapp.core.cache.dao.MenuDao
import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.cache.model.Menu

@Database(
    version = 1,
    entities = [
        Menu::class,
        Cart::class
    ],
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
    abstract fun cartDao(): CartDao
}
