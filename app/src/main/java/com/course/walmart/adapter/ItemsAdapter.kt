package com.course.walmart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.course.walmart.R
import com.course.walmart.model.Product
import com.course.walmart.ui.ProductDetailActivity

class ItemsAdapter(val data: List<Product>, val context: Context) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = data[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(getImageResId(position))


        // sets the text to the textview from our itemHolder class
        holder.tvProdName.text = ItemsViewModel.title
        holder.tvProdColor.text = ItemsViewModel.color
        holder.tvProdPrice.text = ItemsViewModel.price.toString()

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    ProductDetailActivity::class.java
                ).putExtra("product", data[position])
            )
        }
    }

    private fun getImageResId(position: Int): Int {
        return when (position) {
            0 -> R.drawable.bluetooth
            1 -> R.drawable.headphone
            2 -> R.drawable.ps5
            3 -> R.drawable.iphone
            4 -> R.drawable.vr
            else -> R.drawable.logo
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val tvProdName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvProdColor: TextView = itemView.findViewById(R.id.tvProductColor)
        val tvProdPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
    }

}