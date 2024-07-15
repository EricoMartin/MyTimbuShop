package com.basebox.mytimbushop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basebox.mytimbushop.models.Product
import com.basebox.mytimbushop.repo.ProductRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel: ViewModel() {
    private val repository = ProductRepository()

    private val _products = MutableLiveData<Product>()
    val products: LiveData<Product> get() = _products

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchProducts(organizationId: String, appId: String, apiKey: String) {
        repository.getProducts(organizationId, appId, apiKey).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
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
}