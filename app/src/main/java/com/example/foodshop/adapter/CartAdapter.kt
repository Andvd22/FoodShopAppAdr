package com.example.foodshop.adapter

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.R
import com.example.foodshop.model.CartItem

class CartAdapter(private val cartItems: List<CartItem>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.tv_cart_item_name)
        val itemQuantity: TextView = itemView.findViewById(R.id.tv_cart_item_quantity)
        val itemPrice: TextView = itemView.findViewById(R.id.tv_cart_item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]


        holder.itemName.text = currentItem.foodItem.name
        holder.itemQuantity.text = "x ${currentItem.quantity}"

        val linePrice = currentItem.foodItem.price * currentItem.quantity
        holder.itemPrice.text = linePrice.toString()
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    }
