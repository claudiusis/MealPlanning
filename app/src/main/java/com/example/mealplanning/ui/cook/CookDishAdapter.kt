package com.example.mealplanning.ui.cook

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
import com.example.mealplanning.databinding.DishCountViewHolderBinding
import com.example.mealplanning.databinding.DishViewHolderBinding
import com.example.mealplanning.ui.menu_creator.CalendarMenuCreator
import com.example.mealplanning.ui.menu_creator.Dish
import com.example.mealplanning.viewModels.CookViewModel
import com.example.mealplanning.viewModels.CreatorViewModel

class CookDishAdapter(
    private val fragment: CookFragment,
    private val viewModelCook: CookViewModel
) : RecyclerView.Adapter<CookDishAdapter.CookDishViewHolder>() {


    var notesList = listOf<Dish>()
        set(value) {
            val callback = MyDiffUtil(oldArray = field, newArray = value,
                {old, new ->  old.id==new.id})
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)



        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookDishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_count_view_holder, parent, false)
        return CookDishViewHolder(view)
    }

    override fun onBindViewHolder(holder: CookDishViewHolder, position: Int) {
        holder.onBind(notesList[position])

        holder.mBinding.textDishCounter.text = viewModelCook.getTotalStudentDishes().toString()

        if(holder.mBinding.textNameDish.text.equals("Выберите блюдо")) {
            holder.mBinding.infoButton.visibility = View.GONE

        }
        else {
            holder.mBinding.infoButton.visibility = View.VISIBLE
        }


    }

    override fun getItemCount()=notesList.size


    class CookDishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var _binding: DishCountViewHolderBinding
        val mBinding get()=_binding

        init {
            _binding= DishCountViewHolderBinding.bind(itemView)
        }

        private val imageView: ImageView = itemView.findViewById(R.id.imageDish)
        private val nameDish: TextView = itemView.findViewById(R.id.textNameDish)
        private val descriptionDish: TextView = itemView.findViewById(R.id.textDescriptionDish)
        private val dishCount : TextView = itemView.findViewById(R.id.textDishCounter)
        private val infoBtn: ImageButton = itemView.findViewById(R.id.infoButton)
        fun onBind(items: Dish){
            nameDish.text=items.name
            descriptionDish.text=items.ingredients
        }

    }
}
