package com.basebox.mytimbushop.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.basebox.mytimbushop.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getAllProducts(): List<Product>
}