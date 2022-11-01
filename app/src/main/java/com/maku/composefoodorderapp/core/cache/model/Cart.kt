package com.maku.composefoodorderapp.core.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = false)
    val itemId: Long,
    val qty: Int
)
