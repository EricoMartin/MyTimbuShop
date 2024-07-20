package com.basebox.mytimbushop

import android.app.Application
import com.basebox.mytimbushop.data.local.RoomDB
import com.basebox.mytimbushop.repo.OrderRepository
import com.basebox.mytimbushop.repo.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyTimbuApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { RoomDB.getDatabase(this) }
    val repository by lazy { ProductRepository(database.cartItemDao()) }
    val orderRepository by lazy { OrderRepository(database.orderDao()) }
}