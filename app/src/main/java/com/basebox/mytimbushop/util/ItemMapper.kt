package com.basebox.mytimbushop.util

import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Item

fun Item.toCartItem(): CartItem {
    return CartItem(
        id = 0, // id is auto-generated in Room
        name = this.name,
        price = this.currentPrice[0].nGN[0].toString().toDouble(),
        desc = this.description,
        img = this.photos,
        availableQuantity,
        liked = false,
        isAvailable = this.isAvailable,
        userId = this.userId,
        itemId = this.id,
        organizationId = this.organizationId,
    )
}