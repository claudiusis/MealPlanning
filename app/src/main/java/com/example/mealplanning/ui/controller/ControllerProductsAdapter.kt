package com.example.mealplanning.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.MyDiffUtil
import com.example.mealplanning.R

class ControllerProductsAdapter() : RecyclerView.Adapter<ControllerProductsAdapter.ProductViewHolder>() {

    var productList = listOf<Product>()
        set(value) {
            val callback = MyDiffUtil(oldArray = field, newArray = value,
                { old, new -> old.name == new.name },
                {old, new -> old.count==new.count})
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }


    inner class ProductViewHolder (item: View) : RecyclerView.ViewHolder(item) {
        val id = item.findViewById<TextView>(R.id.product_id)
        val name = item.findViewById<TextView>(R.id.product_name)
        val amount = item.findViewById<TextView>(R.id.product_count)

        fun onBind(product : Product){
            name.text = product.name
            amount.text = product.count.toString().plus("штук")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(inflater.inflate(R.layout.product_item_view_holder, parent, false))
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(productList[position])
    }
}