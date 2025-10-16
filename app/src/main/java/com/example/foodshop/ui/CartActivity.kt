package com.example.foodshop.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.R
import com.example.foodshop.adapter.CartAdapter
import com.example.foodshop.utils.CartManager
import com.google.android.material.appbar.MaterialToolbar

class CartActivity : AppCompatActivity() {
    private lateinit var rvCartItems: RecyclerView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnCheckout: Button
    private lateinit var toolbar: MaterialToolbar
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        rvCartItems = findViewById(R.id.rv_cart_items)
        tvTotalPrice = findViewById(R.id.tv_total_price)
        btnCheckout = findViewById(R.id.btn_checkout)
        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            Intent(this, MenuShopActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
        setupRecyclerView()
        updateTotalPrice()

        btnCheckout.setOnClickListener {
            Toast.makeText(this,"Thanh toán thành công ${CartManager.getTotalPrice()}", Toast.LENGTH_SHORT).show()
            CartManager.clearCart()
            setupRecyclerView()
            updateTotalPrice()
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupRecyclerView(){
        val cartItems = CartManager.getCartItems()
        cartAdapter = CartAdapter(cartItems)
        rvCartItems.adapter = cartAdapter
        rvCartItems.layoutManager = LinearLayoutManager(this)

        if(cartItems.isEmpty()){

        }
    }

    private fun updateTotalPrice(){
        val totalPrice = CartManager.getTotalPrice()
        tvTotalPrice.text = totalPrice.toString()
    }


}