package com.basebox.mytimbushop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.Photo
import com.bumptech.glide.Glide

class ImageAdapter(private val itemList: List<Photo>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.img_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = itemList[position]
        Glide.with(holder.imageView.context).load("https://api.timbu.cloud/images/${item.url}").into(holder.imageView)
//        holder.imageView.setImageResource(item.url)
//        Glide.with(holder.itemView.context).load("https://api.timbu.cloud/images/${item.imageResId}").into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}