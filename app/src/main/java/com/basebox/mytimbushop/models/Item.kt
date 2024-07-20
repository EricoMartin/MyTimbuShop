package com.basebox.mytimbushop.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

object AnyParcel : Parceler<Any?> {
    override fun create(parcel: Parcel): Any? {
        return parcel.readString().toString()// Assuming Any is represented as a String. Adjust based on actual type.
    }

    override fun Any?.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this as? String) // Assuming Any is represented as a String. Adjust based on actual type.
    }
}

@Parcelize
@TypeParceler<Any?, AnyParcel>
data class Item(
    @SerializedName("available_quantity")
    val availableQuantity: Int = 0,
    @SerializedName("buying_price")
    val buyingPrice: Any?,
    @SerializedName("categories")
    val categories: List<Any?>,
    @SerializedName("current_price")
    val currentPrice: List<CurrentPrice> = emptyList(),
    @SerializedName("date_created")
    val dateCreated: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("discounted_price")
    val discountedPrice: Any?,
    @SerializedName("extra_infos")
    val extraInfos: Any?,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("is_available")
    val isAvailable: Boolean = false,
    @SerializedName("is_deleted")
    val isDeleted: Boolean = false,
    @SerializedName("is_service")
    val isService: Boolean = false,
    @SerializedName("last_updated")
    val lastUpdated: String = "",
    @SerializedName("name")
    val name: String,
    @SerializedName("organization_id")
    val organizationId: String = "",
    @SerializedName("parent")
    val parent: Any?,
    @SerializedName("parent_product_id")
    val parentProductId: Any?,
    @SerializedName("photos")
    val photos: List<Photo> =  emptyList(),
    @SerializedName("previous_url_slugs")
    val previousUrlSlugs: Any?,
    @SerializedName("product_image")
    val productImage: List<Any?>,
    @SerializedName("selling_price")
    val sellingPrice: Any?,
    @SerializedName("unavailable")
    val unavailable: Boolean = false,
    @SerializedName("unavailable_end")
    val unavailableEnd: Any?,
    @SerializedName("unavailable_start")
    val unavailableStart: Any?,
    @SerializedName("unique_id")
    val uniqueId: String = "",
    @SerializedName("url_slug")
    val urlSlug: String = "",
    @SerializedName("user_id")
    val userId: String  = ""
) : Parcelable