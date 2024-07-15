package com.basebox.mytimbushop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.Items

class CheckoutAdapter (private val arrayList: ArrayList<Items>):
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(products: Items) {
            itemView.findViewById<TextView>(R.id.textViewTitle).text = products.name
            itemView.findViewById<TextView>(R.id.textViewDesc).text = products.desc
            itemView.findViewById<TextView>(R.id.textViewPrice).text = products.price.toString()
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(products.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val list = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return ViewHolder(
            list
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }
}