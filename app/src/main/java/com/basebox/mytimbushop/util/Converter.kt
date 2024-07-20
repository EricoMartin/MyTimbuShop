package com.basebox.mytimbushop.util

import androidx.room.TypeConverter
import com.basebox.mytimbushop.models.Photo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromPhotoList(photos: List<Photo>?): String? {
        if (photos == null) {
            return null
        } else {
            val gson = Gson()
            return gson.toJson(photos)
        }
    }
    @TypeConverter
    fun toPhotoList(photoString: String?): List<Photo>? {
        if (photoString == null) {
            return null
        }
        val gson = Gson()
        val listType = object : TypeToken<List<Photo>>() {}.type
        return gson.fromJson(photoString, listType)
    }
}