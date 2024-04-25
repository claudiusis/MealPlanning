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

class DishAdapter(
    private val fragment: ChooseFoodFragment,
    private val viewModelCreator: CreatorViewModel) : RecyclerView.Adapter<DishAdapter.MyViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_view_holder, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(notesList[position])

        if (shouldHideInfoButtonList.getOrNull(position) == true) {
            holder.mBinding.infoButton.visibility = View.GONE
        } else {
            holder.mBinding.infoButton.visibility = View.VISIBLE
        }

        holder.mBinding.itemDish.setOnClickListener {
            viewModelCreator.replaceDishForChoice(viewModelCreator.getPositionChoice(),notesList[position])
//            viewModelCreator.addSelectedDish(notesList.get(position),viewModelCreator.getPositionChoice())
            fragment.findNavController().navigate(R.id.action_chooseFood_to_calendarMenuCreator)
        }

        holder.mBinding.infoButton.setOnClickListener() {
            viewModelCreator.setPositionChoice(position)
            fragment.findNavController().navigate(R.id.action_chooseFood_to_informationCreatorFragment)

        }
    }

    override fun getItemCount()=notesList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var _binding: DishViewHolderBinding
        val mBinding get()=_binding

        init {
            _binding=DishViewHolderBinding.bind(itemView)
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