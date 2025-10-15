package com.example.foodshop.model

data class FoodItem(
    val id: Int,
    val name: String,
    val descreption: String,
    val price: Double,
    val image: Int,
    val category: String
)