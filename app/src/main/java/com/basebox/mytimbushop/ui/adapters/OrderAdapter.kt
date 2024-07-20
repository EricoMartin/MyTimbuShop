package com.basebox.mytimbushop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.Orders
import com.basebox.mytimbushop.util.Truncator

class OrderAdapter(private val arrayList: List<Orders>):
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(order: Orders) {
            itemView.findViewById<TextView>(R.id.textViewTitle).text =
                Truncator(order.productName.toString(), 16, true).textTruncate()
            itemView.findViewById<TextView>(R.id.textViewDesc).text = order.orderDate.toString()
            itemView.findViewById<TextView>(R.id.textViewPrice).text = order.totalPrice.toString()
            itemView.findViewById<TextView>(R.id.textViewFull).text = order.quantity.toString()

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
        val item = arrayList[position]
        holder.bindItems(arrayList[position])

    }
}