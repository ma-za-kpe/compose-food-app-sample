package com.maku.composefoodorderapp.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.maku.composefoodorderapp.core.cache.model.Menu
import io.reactivex.Flowable

@Dao
interface MenuDao {

    @Transaction
    @Query("SELECT * FROM menu ORDER BY id DESC")
    fun getAllMenuItem(): Flowable<List<Menu>>

    @Query("UPDATE menu SET added=:added WHERE id = :id")
    suspend fun updateMenuItemAdded(added: Boolean, id: Long)

    @Insert
    fun insertMenuItems(vararg menu: Menu)

    @Query("SELECT * FROM menu")
    fun getMenuItemsSync():List<Menu>

}
