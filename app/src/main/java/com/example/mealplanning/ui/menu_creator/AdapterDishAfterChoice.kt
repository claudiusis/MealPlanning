package com.example.mealplanning.ui.menu_creator

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.MyDiffUtil
import com.example.mealplanning.R
import com.example.mealplanning.databinding.DishViewHolderBinding
import com.example.mealplanning.viewModels.CreatorViewModel

class AdapterDishAfterChoice(
    private val fragment: CalendarMenuCreator,
    private val viewModelCreator:CreatorViewModel
) : RecyclerView.Adapter<AdapterDishAfterChoice.AfterChoiceDishViewHolder>() {


    var notesList = listOf<Dish>()
        set(value) {
            val callback = MyDiffUtil(oldArray = field, newArray = value,
                {old, new ->  old.id==new.id})
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)



        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AfterChoiceDishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_view_holder, parent, false)
        return AfterChoiceDishViewHolder(view)
    }

    override fun onBindViewHolder(holder: AfterChoiceDishViewHolder, position: Int) {
        holder.onBind(notesList[position])

<<<<<<< HEAD
=======

        if(holder.mBinding.textNameDish.text.equals("Выберите блюдо")) {
            holder.mBinding.infoButton.visibility = View.GONE

        }
        else {
            holder.mBinding.infoButton.visibility = View.VISIBLE
        }



>>>>>>> 500ce8aa4a6e9054a71b151515d7673cf7446c23
        holder.mBinding.itemDish.setOnClickListener {
            viewModelCreator.setPositionChoice(position)
            fragment.findNavController().navigate(R.id.action_calendarMenuCreator_to_chooseFood)
        }

        holder.mBinding.infoButton.setOnClickListener() {
            viewModelCreator.setPositionChoice(position + 100)
            fragment.findNavController().navigate(R.id.action_calendarMenuCreator_to_informationCreatorFragment)
        }

    }

    override fun getItemCount()=notesList.size




    class AfterChoiceDishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var _binding: DishViewHolderBinding
        val mBinding get()=_binding

        init {
            _binding= DishViewHolderBinding.bind(itemView)
        }

        private val imageView: ImageView = itemView.findViewById(R.id.imageDish)
        private val nameDish: TextView = itemView.findViewById(R.id.textNameDish)
        private val descriptionDish: TextView = itemView.findViewById(R.id.textDescriptionDish)
<<<<<<< HEAD
        private val infoBtn : ImageButton = itemView.findViewById(R.id.infoButton)

=======
        private val infoBtn: ImageButton = itemView.findViewById(R.id.infoButton)
>>>>>>> 500ce8aa4a6e9054a71b151515d7673cf7446c23
        fun onBind(items:Dish){
            nameDish.text=items.name
            descriptionDish.text=items.ingredients

<<<<<<< HEAD
            if (nameDish.text.toString()=="Выберите блюдо"){
                infoBtn.visibility = View.GONE
            } else {
                infoBtn.visibility = View.VISIBLE
            }
=======
>>>>>>> 500ce8aa4a6e9054a71b151515d7673cf7446c23
        }

    }
}
