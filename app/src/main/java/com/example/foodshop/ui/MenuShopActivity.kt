package com.example.foodshop.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodshop.R
import com.example.foodshop.model.FoodItem
import com.example.foodshop.utils.CartManager
import com.example.foodshop.utils.Constants

class MenuShopActivity : AppCompatActivity() {
    private lateinit var foodContainer: LinearLayout
    private lateinit var textName: TextView
    private lateinit var cartIcon: FrameLayout
    private lateinit var cartBadge: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_shop)

        foodContainer = findViewById(R.id.ll_food_categories)
        textName = findViewById(R.id.text_name)
        cartIcon = findViewById(R.id.cart_icon_layout)
        cartBadge = findViewById(R.id.tv_cart_badge)

        textName.text = intent.getStringExtra(Constants.USERNAME)


        val allFoodItems = Constants.getAllFoodItems()
        val groupedFoodItems = allFoodItems.groupBy { it.category }
        displayFoodItemsByCategory(groupedFoodItems, Constants.CATEGORY_MAIN_DISH)
        displayFoodItemsByCategory(groupedFoodItems, Constants.CATEGORY_SIDE_DISH)
        displayFoodItemsByCategory(groupedFoodItems, Constants.CATEGORY_DESSERT)

        cartIcon.setOnClickListener {
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
            }
        }



    }

    override fun onResume() {
        super.onResume()
        updateCartBadge()
    }

    private fun displayFoodItemsByCategory(groupedFoodItems: Map<String, List<FoodItem>>, category: String){
        val foodItemsInCategory = groupedFoodItems[category]
        if(!foodItemsInCategory.isNullOrEmpty()){
            val titleCategory = createCategoryTitleView(category)
            foodContainer.addView(titleCategory)
            for(foodItem in foodItemsInCategory){
                val foodCardView = LayoutInflater.from(this).inflate(R.layout.item_food_card, foodContainer, false)
                val foodImage: ImageView = foodCardView.findViewById(R.id.iv_food_image)
                val foodName: TextView = foodCardView.findViewById(R.id.tv_food_name)
                val foodPrice: TextView = foodCardView.findViewById(R.id.tv_food_price)
                val addToCartButton: Button = foodCardView.findViewById(R.id.btn_add_to_cart)

                foodImage.setImageResource(foodItem.image)
                foodName.text = foodItem.name
                foodPrice.text = foodItem.price.toString()+"đ"

                addToCartButton.setOnClickListener {
                    CartManager.addItem(foodItem)
                    Toast.makeText(this,"Đã thêm", Toast.LENGTH_SHORT).show()
                    updateCartBadge()
                }

                foodContainer.addView(foodCardView)
            }
        }
    }

    private fun createCategoryTitleView(title: String): TextView {
        val textView = TextView(this)
        textView.text = title
        textView.textSize = 22f
        textView.setTypeface(null, android.graphics.Typeface.BOLD)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 48, 0, 16) // top=48px, bottom=16px
        textView.layoutParams = params
        return textView
    }

    private fun updateCartBadge(){
        val totalCount = CartManager.getTotalItemCount()
        if(totalCount > 0){
            cartBadge.visibility = android.view.View.VISIBLE
            cartBadge.text = totalCount.toString()
        }else {
            cartBadge.visibility = android.view.View.GONE
        }
    }


}