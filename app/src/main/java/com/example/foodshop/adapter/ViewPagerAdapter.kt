package com.example.foodshop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodshop.fragments.FoodListFragment
import com.example.foodshop.utils.Constants

class ViewPagerAdapter (
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {
    private val categories = listOf(
        Constants.CATEGORY_MAIN_DISH,
        Constants.CATEGORY_SIDE_DISH,
        Constants.CATEGORY_DESSERT
    )

    override fun createFragment(position: Int): Fragment {
        // Lấy tên danh mục tại vị trí hiện tại
        val category = categories[position]
        // Dùng "nhà máy" newInstance để tạo ra FoodListFragment với đúng danh mục
        return FoodListFragment.newInstance(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }


    fun getCategoryTitle(position: Int): String {
        return categories[position]
    }
}