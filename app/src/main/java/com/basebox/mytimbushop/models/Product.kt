package com.basebox.mytimbushop.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.parcelize.TypeParceler

object AnyParceler : Parceler<Any?> {
    override fun create(parcel: Parcel): Any? {
        return parcel.readString().toString()// Assuming Any is represented as a String. Adjust based on actual type.
    }

    override fun Any?.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this as? String) // Assuming Any is represented as a String. Adjust based on actual type.
    }
}

@Parcelize
@TypeParceler<Any?, AnyParceler>
data class Product(
    @SerializedName("debug")
    val debug: Any?,
    @SerializedName("items")
    val items: List<Item> = emptyList(),
    @SerializedName("next_page")
    val nextPage: Any?,
    @SerializedName("page")
    val page: Int,
    @SerializedName("previous_page")
    val previousPage: Any?,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
): Parcelable
//{
//    override fun hashCode(): Int {
//        var result = 0
//        items.forEach {
//            result = it.id.hashCode()
//            if (items.isNullOrEmpty()) {
//                result = 31 * result + items.hashCode()
//            }
//        }
//        return result
//    }
//}