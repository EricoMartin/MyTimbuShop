package com.basebox.mytimbushop.repo

import com.basebox.mytimbushop.data.RetrofitInstance
import com.basebox.mytimbushop.data.dao.CartItemDao
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class ProductRepository (private  val cartDao: CartItemDao) {
    private val api = RetrofitInstance.api
    val allItems: Flow<List<CartItem>> = cartDao.getAllItems()
    val getItems: Flow<List<CartItem>> = cartDao.getItems()

    suspend fun insertProduct(cartItem: List<CartItem>) {
        cartDao.insert(cartItem)
    }

    fun getProducts(organizationId: String, appId: String, apiKey: String): Call<Product> {
        return api.getProducts(organizationId, appId, apiKey)
    }

    suspend fun updateProduct(cartItem: CartItem) {
        cartDao.update(cartItem)
    }

    suspend fun dbCheckInsert(cartItem: List<CartItem>){
        val newItems = cartItem.filter {
            cartDao.productExists(it.id) == 0
        }
        if (newItems.isNotEmpty()) {
            cartDao.insertAll(newItems)
        }
    }

    suspend fun deleteAllProducts() {
        cartDao.deleteAll()
    }
}