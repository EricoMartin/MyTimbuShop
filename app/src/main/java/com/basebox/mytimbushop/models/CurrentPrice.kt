package com.basebox.mytimbushop.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

object AnyParceled : Parceler<Any?> {
    override fun create(parcel: Parcel): Any? {
        return parcel.readString().toString()// Assuming Any is represented as a String. Adjust based on actual type.
    }

    override fun Any?.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this as? String) // Assuming Any is represented as a String. Adjust based on actual type.
    }
}

@Parcelize
@TypeParceler<Any?, AnyParceled>
data class CurrentPrice(
    @SerializedName("NGN")
    val nGN: List<Any?>
): Parcelable