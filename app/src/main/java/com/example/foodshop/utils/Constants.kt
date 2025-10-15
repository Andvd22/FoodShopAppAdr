package com.example.foodshop.utils

import com.example.foodshop.R
import com.example.foodshop.model.FoodItem

object Constants {

    const val USERNAME = "name"
    const val CATEGORY_MAIN_DISH = "Món chính"
    const val CATEGORY_SIDE_DISH = "Món phụ"
    const val CATEGORY_DESSERT = "Tráng miệng"

    fun getAllFoodItems(): List<FoodItem> {
        val foodItemList: MutableList<FoodItem> = mutableListOf()
        foodItemList.add(FoodItem(1, "Bánh", 50000.0, R.drawable.banh, Constants.CATEGORY_DESSERT))
        foodItemList.add(FoodItem(2,"Bánh sinh nhật",30000.0,R.drawable.banhsinhnhat,CATEGORY_DESSERT))
        foodItemList.add(FoodItem(3,"Cá",40000.0,R.drawable.ca,CATEGORY_MAIN_DISH))
        foodItemList.add(FoodItem(4,"Dưa chuột",30000.0,R.drawable.duachuot,CATEGORY_SIDE_DISH))
        foodItemList.add(FoodItem(5,"Hành",30000.0,R.drawable.hanh,CATEGORY_SIDE_DISH))
        foodItemList.add(FoodItem(6,"Kẹo",30000.0,R.drawable.keo,CATEGORY_DESSERT))
        foodItemList.add(FoodItem(7,"Thịt bò",30000.0,R.drawable.thitbo,CATEGORY_MAIN_DISH))
        foodItemList.add(FoodItem(8,"Thịt xiên",30000.0,R.drawable.thitxien,CATEGORY_MAIN_DISH))
        foodItemList.add(FoodItem(9,"Trứng",30000.0,R.drawable.trung,CATEGORY_SIDE_DISH))
        return foodItemList
    }
}