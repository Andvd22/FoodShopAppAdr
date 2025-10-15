package com.example.foodshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodshop.ui.MenuShopActivity
import com.example.foodshop.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.et_username)
        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener {
            intent = Intent(this@MainActivity, MenuShopActivity::class.java)
            intent.putExtra(Constants.USERNAME, username.text.toString())
            startActivity(intent)
            finish()
        }
    }
}