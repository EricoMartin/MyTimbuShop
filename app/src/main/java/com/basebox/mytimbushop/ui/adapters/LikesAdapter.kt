package com.basebox.mytimbushop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.util.Truncator
import com.bumptech.glide.Glide

class LikesAdapter(private val arrayList: List<CartItem>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<LikesAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onLikeClick(cartItem: CartItem)
        fun onDeleteClick(cartItem: CartItem)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val deleteItem = itemView.findViewById<ImageView>(R.id.delete_item)!!
        val love = itemView.findViewById<ImageView>(R.id.love)!!
        val nameTxt = itemView.findViewById<TextView>(R.id.textViewTitle)
        val priceTxt = itemView.findViewById<TextView>(R.id.textViewPrice)
        val descTxt = itemView.findViewById<TextView>(R.id.textViewDesc)
        val imgView = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val list = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        return ViewHolder(list)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]
        holder.love.setOnClickListener {
            itemClickListener.onLikeClick(item)
        }
        holder.deleteItem.setOnClickListener {
            itemClickListener.onDeleteClick(item)
        }
        holder.nameTxt.text = item.name
        holder.priceTxt.text = item.price.toString()
        holder.descTxt.text = item.desc?.let { Truncator(it, 10, true).textTruncate() }
        Glide.with(holder.itemView.context)
            .load("https://api.timbu.cloud/images/${item.img.first().url}")
            .into(holder.imgView)
    }
}