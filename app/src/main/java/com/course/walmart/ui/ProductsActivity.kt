package com.course.walmart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.course.walmart.R
import com.course.walmart.adapter.ItemsAdapter
import com.course.walmart.model.Product

class ProductsActivity : AppCompatActivity() {
    private val data = arrayListOf<Product>(
        Product(
            "PS 5",
            5000.0,
            "Black",
            "ps5",
            "1",
            "test desc1"
        ),

        Product("Headphone", 2000.0, "Red", "headphone", "2", "test desc2"),
        Product("Earbuds", 1500.0, "Yellow", "bluetooth", "3", "test desc3"),
        Product("Iphone 14", 20000.0, "White", "iphone", "4", "test desc4"),
        Product("VR headset", 1350.0, "White", "vr", "5", "test desc4"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setTitle("Products")
        setProductRecyclerView()

    }

    private fun setProductRecyclerView() {
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        // This will pass the ArrayList to our Adapter
        val adapter = ItemsAdapter(data, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}