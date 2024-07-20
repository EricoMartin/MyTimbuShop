package com.basebox.mytimbushop.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @SerializedName("file_rename")
    val fileRename: Boolean = false,
    @SerializedName("filename")
    val filename: String = "",
    @SerializedName("is_featured")
    val isFeatured: Boolean = false,
    @SerializedName("is_public")
    val isPublic: Boolean = false,
    @SerializedName("model_id")
    val modelId: String = "",
    @SerializedName("model_name")
    val modelName: String = "",
    @SerializedName("organization_id")
    val organizationId: String = "",
    @SerializedName("position")
    val position: Int = 0,
    @SerializedName("save_as_jpg")
    val saveAsJpg: Boolean = false,
    @SerializedName("url")
    val url: String = ""
): Parcelable