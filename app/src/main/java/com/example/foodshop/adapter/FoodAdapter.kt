package com.example.foodshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.R
import com.example.foodshop.model.FoodItem
import com.example.foodshop.utils.CartManager
import java.text.NumberFormat
import java.util.Locale

class FoodAdapter(
    private val foodList: List<FoodItem>,
    private val onCardUpdate: () -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_card,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FoodViewHolder,
        position: Int
    ) {
        val foodItem = foodList[position]
        val context = holder.itemView.context

        holder.foodName.text = foodItem.name
        holder.foodImage.setImageResource(foodItem.image)
        val formatter = NumberFormat.getCurrencyInstance(Locale("vi","VN"))
        holder.foodPrice.text = formatter.format(foodItem.price)

        holder.addToCartButton.setOnClickListener {
            CartManager.addItem(foodItem)
            Toast.makeText(context, "Đã thêm ${foodItem.name} vào giỏ hàng", Toast.LENGTH_SHORT).show()
            onCardUpdate()
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodImage: ImageView = itemView.findViewById(R.id.iv_food_image)
        val foodName: TextView = itemView.findViewById(R.id.tv_food_name)
        val foodPrice: TextView = itemView.findViewById(R.id.tv_food_price)
        val addToCartButton: Button = itemView.findViewById(R.id.btn_add_to_cart)
    }
}