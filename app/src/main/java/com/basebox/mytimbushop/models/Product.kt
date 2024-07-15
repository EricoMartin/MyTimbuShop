package com.basebox.mytimbushop.models


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("debug")
    val debug: Any,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("next_page")
    val nextPage: Any,
    @SerializedName("page")
    val page: Int,
    @SerializedName("previous_page")
    val previousPage: Any,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
)