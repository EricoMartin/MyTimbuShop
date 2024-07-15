package com.basebox.mytimbushop.models


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("file_rename")
    val fileRename: Boolean,
    @SerializedName("filename")
    val filename: String,
    @SerializedName("is_featured")
    val isFeatured: Boolean,
    @SerializedName("is_public")
    val isPublic: Boolean,
    @SerializedName("model_id")
    val modelId: String,
    @SerializedName("model_name")
    val modelName: String,
    @SerializedName("organization_id")
    val organizationId: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("save_as_jpg")
    val saveAsJpg: Boolean,
    @SerializedName("url")
    val url: String
)