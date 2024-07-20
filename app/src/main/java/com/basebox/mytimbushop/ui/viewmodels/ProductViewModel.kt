package com.basebox.mytimbushop.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Product
import com.basebox.mytimbushop.repo.ProductRepository
import com.basebox.mytimbushop.util.toCartItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel(private val repository: ProductRepository,
                       private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {


    private val _products = MutableLiveData<Product>()
    val products: LiveData<Product> get() = _products

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    val allItems: LiveData<List<CartItem>> = repository.allItems.asLiveData()
    val getItems: LiveData<List<CartItem>> = repository.getItems.asLiveData()

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> get() = _cartItems

    fun addLikes(cartItem: CartItem) {
        viewModelScope.launch(dispatcher) {
            val item = CartItem(
                id = 0,
                name = cartItem.name,
                price = cartItem.price,
                desc = cartItem.desc,
                img = cartItem.img,
                availableQuantity = cartItem.availableQuantity,
                isAvailable = cartItem.isAvailable,
                userId = cartItem.userId,
                itemId = cartItem.itemId,
                organizationId = cartItem.organizationId,
                liked = true
            )
            repository.insertProduct(listOf( item))
        }
    }

    fun updateItem(cartItem: CartItem) {
        viewModelScope.launch {
            repository.updateProduct(cartItem)
        }
    }

    fun fetchProducts(organizationId: String, appId: String, apiKey: String) {
            repository.getProducts(organizationId, appId, apiKey)
                .enqueue(object : Callback<Product> {
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
                        if (response.isSuccessful) {
                            viewModelScope.launch(dispatcher) {
                                repository.dbCheckInsert(response.body()!!.items.map {
                                    it.toCartItem()
                                })
                            }
                            _products.postValue(response.body())
                        } else {
                            _error.postValue("Error: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<Product>, err: Throwable) {
                        _error.postValue(err.message)
                    }
                })
    }

    fun deleteAllProducts() {
        viewModelScope.launch {
            repository.deleteAllProducts()
        }
    }
}