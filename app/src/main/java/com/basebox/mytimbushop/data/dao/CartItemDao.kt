package com.basebox.mytimbushop.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.basebox.mytimbushop.models.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {

    @Query("SELECT COUNT(*) FROM cart_item WHERE id = :id")
    suspend fun productExists(id: Int): Int

    @Query("SELECT * FROM cart_item ORDER BY name ASC")
    fun getAllItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(products: List<CartItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: List<CartItem>)

    @Query("SELECT * from cart_item WHERE liked Like :likeString Limit 1")
    fun getItem(likeString: String): CartItem

    @Query("SELECT * from cart_item WHERE liked = 1")
    fun getItems(): Flow<List<CartItem>>

    @Update
    suspend fun update(cartItem: CartItem)

    @Query("DELETE FROM cart_item")
    suspend fun deleteAll()
}