package com.example.mealplanning.ui.menu_creator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val viewModelCreator:CreatorViewModel,
    private val typeDish:String
) : RecyclerView.Adapter<AdapterDishAfterChoice.AfterChoiceDishViewHolder>() {

    private val shouldHideInfoButtonList = mutableListOf<Boolean>()


    var notesList = listOf<Dish>()
        set(value) {
            val callback = MyDiffUtil(oldArray = field, newArray = value,
                {old, new ->  old.id==new.id})
            field = value
            shouldHideInfoButtonList.clear()
            for (dish in value) {
                shouldHideInfoButtonList.add(
                    dish.name == "Выберите первое" ||
                            dish.name == "Выберите второе" ||
                            dish.name == "Выберите напиток"
                )
            }
            notifyDataSetChanged()
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AfterChoiceDishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_view_holder, parent, false)
        return AfterChoiceDishViewHolder(view)
    }

    override fun onBindViewHolder(holder: AfterChoiceDishViewHolder, position: Int) {
        holder.onBind(notesList[position])

        if (shouldHideInfoButtonList.getOrNull(position) == true) {
            holder.mBinding.infoButton.visibility = View.GONE
        } else {
            holder.mBinding.infoButton.visibility = View.VISIBLE
        }

        holder.mBinding.itemDish.setOnClickListener {
            holder.mBinding.itemDish.setOnClickListener {
                viewModelCreator.setPositionChoice(position)
                viewModelCreator.setKeyForDishes(typeDish)
                fragment.findNavController().navigate(R.id.action_calendarMenuCreator_to_chooseFood)
            }
        }

        holder.mBinding.infoButton.setOnClickListener {
            val selectedDishId = notesList[position].id
            if (selectedDishId != null) {
                viewModelCreator.setPositionChoice(selectedDishId)
            }
            viewModelCreator.setKeyForDishes(typeDish)
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

        fun onBind(items:Dish){
            nameDish.text=items.name
            descriptionDish.text=items.ingredients
        }




    }
}
