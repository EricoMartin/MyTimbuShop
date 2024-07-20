package com.basebox.mytimbushop.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "order_history")
data class Orders(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderId: String,
    val productName: String,
    val quantity: Int,
    val orderDate: Date,
    val status: String,
    val totalPrice: Double
)
