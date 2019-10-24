package com.platinumstandard.eonwumah.grouponrecyclerviewpractice.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platinumstandard.eonwumah.grouponrecyclerviewpractice.R
import com.platinumstandard.eonwumah.grouponrecyclerviewpractice.models.Product
import kotlinx.android.synthetic.main.layout_product_list_item.view.*

class ProductRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Product> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_product_list_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ProductViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(productList: List<Product>){
        items = productList
    }

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productName = view.product_name
        val productCategory = view.product_category
        val productPrice = view.product_price
        val productRating = view.product_rating

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            productName.text = product.name
            productCategory.text = "Category: ${product.category.value}"
            productPrice.text = "Price: $${product.price ?: "Not Available"}"
            productRating.text = "Rating: ${product.rating}"

        }
    }


}