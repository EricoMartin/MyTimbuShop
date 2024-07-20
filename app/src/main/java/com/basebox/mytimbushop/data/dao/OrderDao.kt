package com.basebox.mytimbushop.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.basebox.mytimbushop.models.Orders
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(orderHistory: Orders)

    @Query("SELECT * FROM order_history ORDER BY orderDate DESC")
    fun getAllOrders(): Flow<List<Orders>>

    @Query("DELETE FROM order_history")
    suspend fun deleteAllOrders()
}