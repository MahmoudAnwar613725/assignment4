package com.course.walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.course.walmart.databinding.ActivityShoppingCategoryBinding.inflate
import com.course.walmart.databinding.ActivityShoppingCategoryBinding
import com.course.walmart.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUserName(intent.getStringExtra("name"))
        setOnCategoriesClickListners()
    }

    private fun setOnCategoriesClickListners() {

        binding.ivElectronics.setOnClickListener {
            Toast.makeText(this, "you have selected Electronics", Toast.LENGTH_SHORT).show()

        }

        binding.ivClothes.setOnClickListener {
            Toast.makeText(this, "you have selected clothes", Toast.LENGTH_SHORT).show()

        }

        binding.ivBeauty.setOnClickListener {
            Toast.makeText(this, "you have selected beauty", Toast.LENGTH_SHORT).show()

        }

        binding.ivFood.setOnClickListener {
            Toast.makeText(this, "you have selected food", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUserName(stringExtra: String?) {
        binding.tvWelcome.text = "Welcome $stringExtra"

    }

}