package com.example.mealplanning.ui.cook

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentCalendarMenuCreatorBinding
import com.example.mealplanning.databinding.FragmentCookBinding
import com.example.mealplanning.ui.menu_creator.AdapterDishAfterChoice
import com.example.mealplanning.ui.menu_creator.Dish
import com.example.mealplanning.viewModels.CookViewModel
import com.example.mealplanning.viewModels.CreatorViewModel


class CookFragment : Fragment() {
    private var _binding: FragmentCookBinding ?= null
    private val viewModelCook: CookViewModel by activityViewModels<CookViewModel>()

    private lateinit var cookDishAdapter: CookDishAdapter
    private val mBinding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentCookBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()

        val test = ArrayList<Dish>()

        val calendarView = mBinding.calendarViewCook

        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Месяцы в Calendar начинаются с 0
        val currentYear = calendar.get(Calendar.YEAR)

        viewModelCook.setDateCalendar("${currentDay}d${currentMonth}m${currentYear}y")


        val dateChoiceString=viewModelCook.getDateCalendar()

        val calendarChoice = Calendar.getInstance()
        calendar.set(dateChoiceString.substringAfter("m").substringBefore("y").toInt(),
            dateChoiceString.substringAfter("d").substringBefore("m").toInt() - 1,
            dateChoiceString.substringBefore("d").toInt())
        val selectedDateInMillis = calendar.timeInMillis

        calendarView.setDate(selectedDateInMillis, true, true)


        viewModelCook.downloadDishToCook(viewModelCook.getDateCalendar())
        Log.d("zalupa","${viewModelCook.getDateCalendar()}текущая дата")
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "${dayOfMonth}d${month + 1}m${year}y"
            viewModelCook.setDateCalendar(selectedDate)
            //viewModelCook.downLoadDishForChoice()
            viewModelCook.downloadDishToCook(selectedDate)
        }


        cookDishAdapter = CookDishAdapter(this, viewModelCook)
        mBinding.recyclerCookInfo.layoutManager= LinearLayoutManager(requireContext())
        viewModelCook.getDishToCookLiveData().observe(
            viewLifecycleOwner,
        ){
                array->cookDishAdapter.notesList=array
            cookDishAdapter.notifyDataSetChanged()
            Log.d("zalupa","${cookDishAdapter.notesList}Массив в адаптере")
        }
        mBinding.recyclerCookInfo.adapter=cookDishAdapter
//        dishAfterChoiceRecyclerAdapter.notesList=viewModelCreator.getSelectedDish()
        return mBinding.root


    }

}