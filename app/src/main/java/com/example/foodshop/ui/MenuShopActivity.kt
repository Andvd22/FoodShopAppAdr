package com.example.foodshop.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.foodshop.R
import com.example.foodshop.adapter.ViewPagerAdapter
import com.example.foodshop.fragments.FoodListFragment
import com.example.foodshop.utils.CartManager
import com.example.foodshop.utils.Constants
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuShopActivity : AppCompatActivity(), FoodListFragment.OnCartUpdateListener {
    // Xóa: private lateinit var foodContainer: LinearLayout

    private lateinit var textName: TextView
    private lateinit var cartIcon: FrameLayout
    private lateinit var cartBadge: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_shop)

        // Kết nối các View
        textName = findViewById(R.id.text_name)
        cartIcon = findViewById(R.id.cart_icon_layout)
        cartBadge = findViewById(R.id.tv_cart_badge)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        // Gán tên người dùng
        textName.text = intent.getStringExtra(Constants.USERNAME)

        // Thiết lập Adapter cho ViewPager
        val pagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // Kết nối TabLayout với ViewPager2
        // Thao tác này sẽ tự động tạo các tab với tiêu đề tương ứng
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pagerAdapter.getCategoryTitle(position) // Cần thêm hàm này vào ViewPagerAdapter
        }.attach()

        // Xử lý sự kiện click giỏ hàng
        cartIcon.setOnClickListener {
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    // Luôn cập nhật badge khi quay lại Activity này
    override fun onResume() {
        super.onResume()
        updateCartBadge()
    }

    // Xóa các hàm: displayFoodItemsByCategory, createCategoryTitleView

    private fun updateCartBadge() {
        val totalCount = CartManager.getTotalItemCount()
        if (totalCount > 0) {
            cartBadge.visibility = View.VISIBLE
            cartBadge.text = totalCount.toString()
        } else {
            cartBadge.visibility = View.GONE
        }
    }

    // Đây là hàm được gọi từ Fragment thông qua Interface
    override fun onCartUpdate() {
        updateCartBadge()
    }
}
