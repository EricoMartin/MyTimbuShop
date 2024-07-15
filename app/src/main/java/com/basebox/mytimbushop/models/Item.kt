package com.basebox.mytimbushop.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("buying_price")
    val buyingPrice: Any,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("current_price")
    val currentPrice: List<CurrentPrice>,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("discounted_price")
    val discountedPrice: Any,
    @SerializedName("extra_infos")
    val extraInfos: Any,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("is_deleted")
    val isDeleted: Boolean,
    @SerializedName("is_service")
    val isService: Boolean,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("organization_id")
    val organizationId: String,
    @SerializedName("parent")
    val parent: Any,
    @SerializedName("parent_product_id")
    val parentProductId: Any,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("previous_url_slugs")
    val previousUrlSlugs: Any,
    @SerializedName("product_image")
    val productImage: List<Any>,
    @SerializedName("selling_price")
    val sellingPrice: Any,
    @SerializedName("unavailable")
    val unavailable: Boolean,
    @SerializedName("unavailable_end")
    val unavailableEnd: Any,
    @SerializedName("unavailable_start")
    val unavailableStart: Any,
    @SerializedName("unique_id")
    val uniqueId: String,
    @SerializedName("url_slug")
    val urlSlug: String,
    @SerializedName("user_id")
    val userId: String
)