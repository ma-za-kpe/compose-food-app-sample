package com.maku.composefoodorderapp.core.cache.dao

import androidx.room.*
import com.maku.composefoodorderapp.core.cache.model.Cart
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Transaction
    @Query("SELECT * FROM cart ORDER BY itemId DESC")
    fun getAllCartItem(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(
        item: Cart
    )

    @Query("DELETE FROM cart")
    suspend fun nukeCartTable()

    @Query("DELETE FROM cart WHERE itemId = :id")
    suspend fun deleteCartItemById(id: Long)

    @Query("UPDATE cart SET qty=:qty WHERE itemId = :id")
    suspend fun updateCartItemQty(qty: Int, id: Long)

}
