package com.basebox.mytimbushop.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Double = 0.0,
    val desc: String? = "",
    val img: List<Photo>,
    val availableQuantity: Int,
    val isAvailable: Boolean,
    val userId: String,
    val itemId: String,
    val organizationId: String,
    var liked: Boolean = false
)