package com.example.mealplanning.ui.student.menu
import com.example.mealplanning.ui.menu_creator.Dish
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
import com.example.mealplanning.viewModels.StudentViewModel

class AdapterStudentMakeChoice(
    private val fragment: StudentMakeChoiceFragment,
    private val viewModelStudent: StudentViewModel
) : RecyclerView.Adapter<AdapterStudentMakeChoice.StudentMakeChoiceViewHolder>() {


    var notesList = listOf<Dish>()
        set(value) {
            val callback = MyDiffUtil(oldArray = field, newArray = value,
                {old, new ->  old.id==new.id})
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentMakeChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_view_holder, parent, false)
        return StudentMakeChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentMakeChoiceViewHolder, position: Int) {
        holder.onBind(notesList[position])

        if(holder.mBinding.textNameDish.text.equals("Выберите первое") ||
            holder.mBinding.textNameDish.text.equals("Выберите второе") ||
            holder.mBinding.textNameDish.text.equals("Выберите третье") ||
            holder.mBinding.textNameDish.text.equals("Будет скоро") ||
            holder.mBinding.textNameDish.text.equals("Выберите блюдо")) {
            holder.mBinding.infoButton.visibility = View.GONE

        }
        else {
            holder.mBinding.infoButton.visibility = View.VISIBLE
        }


        holder.mBinding.itemDish.setOnClickListener {
            viewModelStudent.replaceDishForChoice(viewModelStudent.getPositionChoice(),notesList[position])
            fragment.findNavController().navigate(R.id.action_studentMakeChoiceFragment_to_menuFragment)
        }
        holder.mBinding.infoButton.setOnClickListener() {
            viewModelStudent.setPositionChoice(position)
            fragment.findNavController().navigate(R.id.action_studentMakeChoiceFragment_to_informationFragment)
        }

    }

    override fun getItemCount()=notesList.size




    class StudentMakeChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var _binding: DishViewHolderBinding
        val mBinding get()=_binding

        init {
            _binding= DishViewHolderBinding.bind(itemView)
        }

        private val imageView: ImageView = itemView.findViewById(R.id.imageDish)
        private val nameDish: TextView = itemView.findViewById(R.id.textNameDish)
        private val descriptionDish: TextView = itemView.findViewById(R.id.textDescriptionDish)

        fun onBind(items: Dish){
            nameDish.text=items.name
            descriptionDish.text=items.ingredients
        }


    }
}
