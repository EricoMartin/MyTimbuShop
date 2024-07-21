package com.basebox.mytimbushop.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Item
import com.basebox.mytimbushop.models.Product
import com.bumptech.glide.Glide

private const val TAG = "ProductAdapter"
class ProductAdapter (private val products: Product, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Item, position: Int)
        fun onCartClick(cartItem: CartItem)
        fun onLikeClick(cartItem: CartItem)
    }
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val imgCart: ImageView = itemView.findViewById(R.id.cart_img)
        val loveCart: ImageView = itemView.findViewById(R.id.imageView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.items[position]
        holder.textViewTitle.text = product.name
        holder.textViewPrice.text = "₦ ${product.currentPrice[0].nGN[0].toString()}"
        holder.imgCart.setOnClickListener {
            val cartItem = CartItem(
                id = 0,
                name = product.name,
                price = product.currentPrice[0].nGN[0].toString().toDouble(),
                desc = product.description,
                img = product.photos,
                availableQuantity = product.availableQuantity,
                isAvailable = product.isAvailable,
                userId = product.userId,
                itemId = product.id,
                organizationId = product.organizationId,
                liked = true
            )
            Log.d(TAG, "CartItem: $cartItem")
            itemClickListener.onCartClick(cartItem)
        }
        Glide.with(holder.itemView.context).load("https://api.timbu.cloud/images/${product.photos.first().url}").into(holder.imageView)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(product, position)
        }
        holder.loveCart.setOnClickListener {
            holder.itemView.findViewById<ImageView>(R.id.imageView5).setImageResource(R.drawable.red_love)
            val cartItem = CartItem(
                id = 0,
                name = product.name,
                price = product.currentPrice[0].nGN[0].toString().toDouble(),
                desc = product.description,
                img = product.photos,
                availableQuantity = product.availableQuantity,
                isAvailable = product.isAvailable,
                userId = product.userId,
                itemId = product.id,
                organizationId = product.organizationId,
                liked = false
            )
            itemClickListener.onLikeClick(cartItem)
        }
    }

    override fun getItemCount() = products.items.size
}