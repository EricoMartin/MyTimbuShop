package com.basebox.mytimbushop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.basebox.mytimbushop.models.CartItem

class CartViewModel: ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> get() = _cartItems

    fun addCartItem(item: CartItem) {
        _cartItems.value?.add(item)
        _cartItems.value = _cartItems.value // Trigger LiveData update
    }

    fun removeCartItem(item: CartItem) {
        _cartItems.value?.remove(item)
        _cartItems.value = _cartItems.value // Trigger LiveData update
    }
}