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

class CheckoutAdapter(private val arrayList: List<CartItem>) :
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        fun bindItems(products: CartItem) {
            itemView.findViewById<TextView>(R.id.textViewTitle).text = products.name
            itemView.findViewById<TextView>(R.id.textViewDesc).text =
                Truncator(products.desc.toString(), 16, true).textTruncate()
            itemView.findViewById<TextView>(R.id.textViewPrice).text = products.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val list =
            LayoutInflater.from(parent.context).inflate(R.layout.checkout_list_item, parent, false)
        return ViewHolder(
            list
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = arrayList[position]
        holder.bindItems(arrayList[position])
        Glide.with(holder.itemView.context)
            .load("https://api.timbu.cloud/images/${product.img.first()}").into(holder.imageView)
    }
}