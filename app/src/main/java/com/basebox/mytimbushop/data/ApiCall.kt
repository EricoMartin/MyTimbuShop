package com.basebox.mytimbushop.data

import com.basebox.mytimbushop.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    @GET("/products")
    fun getProducts(
        @Query("organization_id") organization: String,
        @Query("Appid") appId: String,
        @Query("Apikey") apiKey: String
    ): Call<Product>
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.timbu.cloud"

    val api: ApiCall by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ApiCall::class.java)
    }
}