package com.basebox.mytimbushop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.Photo
import com.basebox.mytimbushop.models.Photos
import com.bumptech.glide.Glide

class ImageAdapter(private val itemList: List<Photos>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.img_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageView.setImageResource(item.imageResId)
//        Glide.with(holder.itemView.context).load("https://api.timbu.cloud/images/${item.imageResId}").into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}