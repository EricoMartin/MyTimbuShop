package com.basebox.mytimbushop.repo

import com.basebox.mytimbushop.data.dao.OrderDao
import com.basebox.mytimbushop.models.Orders
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao) {
    val allOrders: Flow<List<Orders>> = orderDao.getAllOrders()

    suspend fun insert(orderHistory: Orders) {
        orderDao.insertOrder(orderHistory)
    }

    suspend fun deleteAll() {
        orderDao.deleteAllOrders()
    }
}