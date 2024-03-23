package com.example.mealplanning.ui.menu_creator

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentCalendarMenuCreatorBinding
import com.example.mealplanning.viewModels.CreatorViewModel


class CalendarMenuCreator : Fragment() {

    private var _binding: FragmentCalendarMenuCreatorBinding?=null
    private val viewModelCreator: CreatorViewModel by activityViewModels<CreatorViewModel>()
    private lateinit var dishAfterChoiceRecyclerAdapter:AdapterDishAfterChoice
    private val mBinding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentCalendarMenuCreatorBinding.inflate(inflater, container, false)
        viewModelCreator.downLoadAllDish()
        viewModelCreator.setSelectedDish(viewModelCreator.getListAfterChoice())

        val calendar=Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Месяцы в Calendar начинаются с 0
        val currentYear = calendar.get(Calendar.YEAR)
        viewModelCreator.setDateCalendar("$currentDay/$currentMonth/$currentYear")


        val calendarView=mBinding.calendarView
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            viewModelCreator.setDateCalendar(selectedDate)
        }


        dishAfterChoiceRecyclerAdapter= AdapterDishAfterChoice(this,viewModelCreator)
        mBinding.chooseFoodRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        mBinding.chooseFoodRecyclerView.adapter=dishAfterChoiceRecyclerAdapter
        dishAfterChoiceRecyclerAdapter.notesList=viewModelCreator.getSelectedDish()
        Log.d("FENIX","${dishAfterChoiceRecyclerAdapter.notesList}")





        mBinding.btnConfirm.setOnClickListener {
            viewModelCreator.upLoadSelectedDish()
        }


        return mBinding.root
    }



}