package com.basebox.mytimbushop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.CartItem
import com.bumptech.glide.Glide

class CartAdapter(private val arrayList: List<CartItem>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onCartClick(cartItem: CartItem)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.delete_item)
        fun bindItems(products: CartItem) {
            itemView.findViewById<TextView>(R.id.textViewTitle).text = products.name
//            itemView.findViewById<TextView>(R.id.textViewDesc).text = products.desc
            itemView.findViewById<TextView>(R.id.textViewPrice).text = products.price.toString()

            val imgView = itemView.findViewById<ImageView>(R.id.imageView)
            Glide.with(itemView.context)
                .load("https://api.timbu.cloud/images/${products.img.first().url}")
                .into(imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val list = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        return ViewHolder(
            list
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]
        holder.bindItems(arrayList[position])
        holder.deleteButton.setOnClickListener {
            itemClickListener.onCartClick(item)
        }
    }
}