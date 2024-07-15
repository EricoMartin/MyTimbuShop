package com.basebox.mytimbushop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.Product
import com.bumptech.glide.Glide

class ProductAdapter (private val products: Product): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.items[position]
        holder.textViewTitle.text = product.name
        holder.textViewPrice.text = "₦ ${product.currentPrice[0].nGN[0].toString()}"
        Glide.with(holder.itemView.context).load("https://api.timbu.cloud/images/${product.photos.first().url}").into(holder.imageView)
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }
    }

    override fun getItemCount() = products.items.size
}