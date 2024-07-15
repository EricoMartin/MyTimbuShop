package com.basebox.mytimbushop.repo

import com.basebox.mytimbushop.data.RetrofitInstance
import com.basebox.mytimbushop.models.Product
import retrofit2.Call

class ProductRepository {
    private val api = RetrofitInstance.api

    fun getProducts(organizationId: String, appId: String, apiKey: String): Call<Product> {
        return api.getProducts(organizationId, appId, apiKey)
    }
}