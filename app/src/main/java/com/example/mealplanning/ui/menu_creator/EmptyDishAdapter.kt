package com.example.mealplanning.ui.menu_creator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.R

class EmptyDishAdapter(private val data: List<String>) : RecyclerView.Adapter<EmptyDishAdapter.EmptyDishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyDishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_empty_view_holder, parent, false)
        return EmptyDishViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmptyDishViewHolder, position: Int) {
        holder.textNameDish.text = data[position]
        //holder.imageDish.setImageResource()
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class EmptyDishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageDish: ImageView = itemView.findViewById(R.id.imageDish)
        val textNameDish: TextView = itemView.findViewById(R.id.textNameDish)
    }
}
