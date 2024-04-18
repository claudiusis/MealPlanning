package com.example.mealplanning.ui.menu_creator

import android.annotation.SuppressLint
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
    private lateinit var chooseDishCreatorAdapter: ChooseDishCreatorAdapter
    private val mBinding get()=_binding!!


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentCalendarMenuCreatorBinding.inflate(inflater, container, false)

        viewModelCreator.downLoadAllDish()



        val calendar=Calendar.getInstance()

        val calendarView=mBinding.calendarView

        val dateChoiceString=viewModelCreator.getDateCalendar()

        calendar.set(dateChoiceString.substringAfter("m").substringBefore("y").toInt(),
            dateChoiceString.substringAfter("d").substringBefore("m").toInt() - 1,
            dateChoiceString.substringBefore("d").toInt())
        val selectedDateInMillis = calendar.timeInMillis

        calendarView.setDate(selectedDateInMillis, true, true)


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->

            val selectedDate = "${dayOfMonth}d${month + 1}m${year}y"
            viewModelCreator.setDateCalendar(selectedDate)
            viewModelCreator.downLoadDishForChoice()

        }

        chooseDishCreatorAdapter = ChooseDishCreatorAdapter(this, viewModelCreator, viewLifecycleOwner)
        chooseDishCreatorAdapter.listOfDishes = listOf(
            ChooseDish("Первое", arrayListOf()),
            ChooseDish("Второе", arrayListOf()),
            ChooseDish("Напиток", arrayListOf())
        )
        mBinding.chooseFoodRecyclerView.adapter = chooseDishCreatorAdapter
        mBinding.chooseFoodRecyclerView.layoutManager = LinearLayoutManager(this.context)


//        dishAfterChoiceRecyclerAdapter.notesList=viewModelCreator.getSelectedDish()

        mBinding.btnConfirm.setOnClickListener {
            viewModelCreator.upLoadSelectedDish()
        }

        return mBinding.root
    }



}