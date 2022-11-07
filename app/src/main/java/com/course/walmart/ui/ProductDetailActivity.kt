package com.course.walmart.ui

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.course.walmart.R
import com.course.walmart.databinding.ActivityProductDetailBinding
import com.course.walmart.databinding.ActivityProductDetailBinding.inflate
import com.course.walmart.model.Product

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            var product = bundle.get("product") as Product
            setTitle(product.title)

            binding.tvProdTitle.text = product.title
            binding.tvProdPrice.text = "$ ${product.price}"
            binding.tvProdNumber.text = "Number : ${product.itemId}"
            binding.tvPRodColor.text = "color : ${product.color}"
            binding.tvProdDescription.text = "Description : ${product.desc}"

            when (product.itemId) {
                "1" -> binding.detailProductImage.setImageResource(R.drawable.bluetooth)
                "2" -> binding.detailProductImage.setImageResource(R.drawable.headphone)
                "3" -> binding.detailProductImage.setImageResource(R.drawable.ps5)
                "4" -> binding.detailProductImage.setImageResource(R.drawable.iphone)
                "5" -> binding.detailProductImage.setImageResource(R.drawable.vr)
            }

        } else {
            Toast.makeText(this, "no detail found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}