package com.example.foodshop.utils

import com.example.foodshop.model.CartItem
import com.example.foodshop.model.FoodItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(foodItem: FoodItem){
        val existingItem = cartItems.find { it.foodItem.id == foodItem.id }

        if(existingItem != null){
            existingItem.quantity++
        }else{
            cartItems.add(CartItem( foodItem, 1))
        }
    }

    fun getCartItems(): List<CartItem> {
        return cartItems.toList()
    }

    fun getTotalItemCount(): Int {
//        return cartItems.sumOf { it.quantity }
        return cartItems.size
    }

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.foodItem.price * it.quantity }
    }

    fun clearCart(){
        cartItems.clear()
    }
}